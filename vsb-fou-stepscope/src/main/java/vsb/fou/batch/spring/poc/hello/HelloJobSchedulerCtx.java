package vsb.fou.batch.spring.poc.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import vsb.fou.batch.spring.poc.util.TimeStamp;

import javax.annotation.Resource;

@Configuration
public class HelloJobSchedulerCtx {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloJobSchedulerCtx.class);

    @Resource
    private JobLauncher jobLauncher;
    @Resource
    private Job helloStepScopeJob;

    @Scheduled(fixedRate = 15 * 1000L)
    public void helloStep() {
        JobParametersBuilder builder = new JobParametersBuilder().addString("timestamp", TimeStamp.getTstamp());
        try {
            JobParameters params = builder.toJobParameters();
            LOGGER.info("Starter helloStepScopeJob, med params: " + params);
            jobLauncher.run(helloStepScopeJob, params);
        } catch (Exception e) {
            LOGGER.error("helloJob feilet!", e);
        }
    }

}
