package vsb.fou.batch.spring.quartz;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vegard S. Bye
 */
@Configuration
public class JobDetailCtx {

    @Autowired
    private JobLocator jobLocator;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    @Qualifier("importProductsJob")
    private Job job;

    @Bean
    public JobDetailFactoryBean springBatchQuartzJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(SpringBatchQuartzJob.class);
        Map<String, Object> map = new HashMap<>();
        map.put("jobLocator", jobLocator);
        map.put("jobLauncher", jobLauncher);
        map.put(SpringBatchQuartzJob.JOB_NAME, job.getName());
        bean.setJobDataAsMap(map);
        bean.setDurability(true);
        return bean;
    }
}
