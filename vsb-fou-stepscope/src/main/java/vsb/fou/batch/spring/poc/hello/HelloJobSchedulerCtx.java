package vsb.fou.batch.spring.poc.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class HelloJobSchedulerCtx {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloJobSchedulerCtx.class);

    @Resource
    private JobLauncher jobLauncher;
    @Resource
    private Job helloStepScopeJob;

    @Scheduled(fixedRate = 15 * 1000L)
    public void helloStep() {
        JobParametersBuilder builder = new JobParametersBuilder().addString("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        try {
            JobParameters params = builder.toJobParameters();
            System.out.println("+------------------------------------------");
            System.out.println("| Starter helloStepScopeJob, med params: " + params);
            System.out.println("+------------------------------------------");
            LOGGER.info("Starter helloStepScopeJob, med params: " + params);
            jobLauncher.run(helloStepScopeJob, params);
        } catch (Exception e) {
            LOGGER.error("helloJob feilet!", e);
        }
    }

}
