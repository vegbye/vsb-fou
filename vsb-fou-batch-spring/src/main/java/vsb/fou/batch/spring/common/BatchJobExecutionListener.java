package vsb.fou.batch.spring.common;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Vegard S. Bye
 */
@Component
public class BatchJobExecutionListener implements JobExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchJobExecutionListener.class);

    private static String getRootCauseStackTrace(List<Throwable> throwables) {
        StringBuilder sb = new StringBuilder();
        for (Throwable t : throwables) {
            String[] rootCauseStackTrace = ExceptionUtils.getRootCauseStackTrace(t);
            for (String s : rootCauseStackTrace) {
                sb.append(s).append("\n");
            }
        }
        return sb.toString().trim();
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        Long jobInstanceId = jobExecution.getJobInstance().getId();
        Long jobExecutionId = jobExecution.getId();
        JobParameters jobParameters = jobExecution.getJobParameters();
        LOGGER.info("Starter jobb:" + jobName + " jobInstanceId:" + jobInstanceId + " jobExecutionId:" + jobExecutionId + " " +
                "JobParameters:" + jobParameters);
        if (isSmoketest(jobExecution)) {
            jobExecution.stop();
        }
    }

    private boolean isSmoketest(JobExecution jobExecution) {
        JobParameters jobParameters = jobExecution.getJobParameters();
        return Boolean.TRUE.toString().equalsIgnoreCase(jobParameters.getString("smoketest", "false"));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        Long jobInstanceId = jobExecution.getJobInstance().getId();
        Long jobExecutionId = jobExecution.getId();
        LOGGER.info("-------------- jobExecution.getAllFailureExceptions():" + jobExecution.getAllFailureExceptions());
        boolean smoketest = isSmoketest(jobExecution);
        if (jobExecution.getStatus().isUnsuccessful()) {
            String stackTrace = getRootCauseStackTrace(jobExecution.getAllFailureExceptions());
            LOGGER.error("FAILED! jobb:" + jobName
                    + " Smoketest:" + smoketest
                    + " jobInstanceId:" + jobInstanceId
                    + " jobExecutionId:" + jobExecutionId
                    + " Status:" + jobExecution.getStatus()
                    + " ExitStatus:" + jobExecution.getExitStatus()
                    + "\n" + stackTrace);
        } else {
            if (jobExecution.getStatus() == BatchStatus.STOPPED && smoketest) {
                jobExecution.setStatus(BatchStatus.COMPLETED);
                jobExecution.setExitStatus(ExitStatus.COMPLETED);
            }
            LOGGER.info("SUCCESS! jobb:" + jobName
                    + " Smoketest:" + smoketest
                    + " jobInstanceId:" + jobInstanceId
                    + " jobExecutionId:" + jobExecutionId
                    + " Status:" + jobExecution.getStatus()
                    + " ExitStatus:" + jobExecution.getExitStatus());
        }
    }
}
