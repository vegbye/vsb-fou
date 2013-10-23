package vsb.fou.batch.spring.quartz;

import org.quartz.JobExecutionContext;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

public class SpringBatchQuartzJob extends QuartzJobBean {

    private static final String JOB_NAME = "jobName";
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
            System.out.println("0");
            Map<String, Object> jobDataMap = context.getMergedJobDataMap();
            System.out.println("1:" + jobDataMap);
            String jobName = (String) jobDataMap.get(JOB_NAME);
            System.out.println("2:" + jobName);
            JobParameters jobParameters = getJobParametersFromJobMap(jobDataMap);
            System.out.println("3:" + jobParameters);
            jobLauncher.run(jobLocator.getJob(jobName), jobParameters);
            System.out.println("4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get params from jobDataAsMap property, job-quartz.xml
    private JobParameters getJobParametersFromJobMap(Map<String, Object> jobDataMap) {

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
        builder.addDate("run date", new Date());

        return builder.toJobParameters();

    }

}
