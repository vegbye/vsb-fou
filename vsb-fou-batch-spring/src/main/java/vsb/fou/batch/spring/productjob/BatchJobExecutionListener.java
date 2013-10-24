package vsb.fou.batch.spring.productjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author Vegard S. Bye
 */
@Component
public class BatchJobExecutionListener implements JobExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchJobExecutionListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        Long jobInstanceId = jobExecution.getJobInstance().getId();
        Long jobExecutionId = jobExecution.getId();
        LOGGER.info("Starter jobb:" + jobName + " jobInstanceId:" + jobInstanceId + " jobExecutionId:" + jobExecutionId);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        Long jobInstanceId = jobExecution.getJobInstance().getId();
        Long jobExecutionId = jobExecution.getId();
        if (jobExecution.getStatus().isUnsuccessful()) {
            LOGGER.info("FAILED! jobb:" + jobName + " jobInstanceId:" + jobInstanceId + " jobExecutionId:" + jobExecutionId);
        } else {
            LOGGER.info("SUCCESS! jobb:" + jobName + " jobInstanceId:" + jobInstanceId + " jobExecutionId:" + jobExecutionId);
        }
    }
}
