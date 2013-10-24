package vsb.fou.batch.spring.quartz;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.Map.Entry;
import java.util.UUID;

public class SpringBatchQuartzJob extends QuartzJobBean {

    public static final String JOB_NAME = "jobName";
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchQuartzJob.class);
    private JobLocator jobLocator;
    private JobLauncher jobLauncher;

    @SuppressWarnings("UnusedDeclaration")
    public void setJobLocator(JobLocator jobLocator) {
        this.jobLocator = jobLocator;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    protected void executeInternal(JobExecutionContext context) {
        try {
            JobDataMap jobDataMap = context.getMergedJobDataMap();
            LOGGER.info("JobDataMap fra context:");
            for (Entry<String, Object> entry : jobDataMap.entrySet()) {
                LOGGER.info(entry.getKey() + " => " + entry.getValue());
            }
            String jobName = (String) jobDataMap.get(JOB_NAME);
            LOGGER.info("jobName:" + jobName);
            JobParameters jobParameters = getJobParametersFromJobMap(jobDataMap);
            LOGGER.info("JobParameters:" + jobParameters);
            JobExecution jobExecution = jobLauncher.run(jobLocator.getJob(jobName), jobParameters);
            LOGGER.info("JobExecution.status:" + jobExecution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get params from jobDataAsMap property, job-quartz.xml
    private JobParameters getJobParametersFromJobMap(JobDataMap jobDataMap) {

        JobParametersBuilder builder = new JobParametersBuilder();

        for (Entry<String, Object> entry : jobDataMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String && !key.equals(JOB_NAME)) {
                builder.addString(key, (String) value);
            } else if (value instanceof Float || value instanceof Double) {
                builder.addDouble(key, ((Number) value).doubleValue());
            } else if (value instanceof Integer || value instanceof Long) {
                builder.addLong(key, ((Number) value).longValue());
            } else if (value instanceof Date) {
                builder.addDate(key, (Date) value);
            } else {
                // JobDataMap contains values which are not job parameters
                // (ignoring)
            }
        }

        //need unique job parameter to rerun the completed job
        builder.addString("ID", UUID.randomUUID().toString());

        return builder.toJobParameters();

    }

}
