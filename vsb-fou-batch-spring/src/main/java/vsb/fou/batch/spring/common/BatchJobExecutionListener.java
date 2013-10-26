package vsb.fou.batch.spring.common;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
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
        LOGGER.info("Starter jobb:" + jobName + " jobInstanceId:" + jobInstanceId + " jobExecutionId:" + jobExecutionId);
        try {
            if ("true".equalsIgnoreCase(jobExecution.getJobInstance().getJobParameters().getString("smoketest", "false"))) {
                jobExecution.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        Long jobInstanceId = jobExecution.getJobInstance().getId();
        Long jobExecutionId = jobExecution.getId();
        LOGGER.info("-------------- jobExecution.getAllFailureExceptions():" + jobExecution.getAllFailureExceptions());
        if (jobExecution.getStatus().isUnsuccessful()) {
            String stackTrace = getRootCauseStackTrace(jobExecution.getAllFailureExceptions());
            LOGGER.error("FAILED! jobb:" + jobName
                    + " jobInstanceId:" + jobInstanceId
                    + " jobExecutionId:" + jobExecutionId
                    + " Status:" + jobExecution.getStatus()
                    + " ExitStatus:" + jobExecution.getExitStatus()
                    + "\n" + stackTrace);
        } else {
            LOGGER.info("SUCCESS! jobb:" + jobName
                    + " jobInstanceId:" + jobInstanceId
                    + " jobExecutionId:" + jobExecutionId
                    + " Status:" + jobExecution.getStatus()
                    + " ExitStatus:" + jobExecution.getExitStatus());
        }
    }
}
