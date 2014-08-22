package vsb.fou.batch.spring.poc.importpersons;

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
public class ImportPersonsJobSchedulerCtx {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportPersonsJobSchedulerCtx.class);

    @Resource
    private JobLauncher jobLauncher;
    @Resource
    private Job importPersonsJob;

    @Scheduled(fixedRate = 15 * 1000L)
    public void importPersons() {
        JobParametersBuilder builder = new JobParametersBuilder().addString("timestamp", TimeStamp.getTstamp());
        try {
            JobParameters params = builder.toJobParameters();
            System.out.println("+------------------------------------------");
            System.out.println("| Starter importPersonsJob, med params: " + params);
            System.out.println("+------------------------------------------");
            LOGGER.info("Starter importPersonsJob, med params: " + params);
            jobLauncher.run(importPersonsJob, params);
        } catch (Exception e) {
            LOGGER.error("ImportPersonsJobb feilet!", e);
        }
    }

}
