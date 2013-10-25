package vsb.fou.batch.spring.quartz;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import javax.annotation.Resource;
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
    @Resource(name = "importProductsJob")
    private Job importProductsJob;
    @Resource(name = "helloJob")
    private Job helloJob;
    @Resource(name = "heiJobb")
    private Job heiJobb;

    @Bean
    public JobDetailFactoryBean heiQuartzJobb() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(SpringBatchQuartzJob.class);
        Map<String, Object> map = new HashMap<>();
        map.put("jobLocator", jobLocator);
        map.put("jobLauncher", jobLauncher);
        map.put(SpringBatchQuartzJob.JOB_NAME, heiJobb.getName());
        bean.setJobDataAsMap(map);
        bean.setDurability(true);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean helloQuartzJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(SpringBatchQuartzJob.class);
        Map<String, Object> map = new HashMap<>();
        map.put("jobLocator", jobLocator);
        map.put("jobLauncher", jobLauncher);
        map.put(SpringBatchQuartzJob.JOB_NAME, helloJob.getName());
        bean.setJobDataAsMap(map);
        bean.setDurability(true);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean importProductsQuartzJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(SpringBatchQuartzJob.class);
        Map<String, Object> map = new HashMap<>();
        map.put("jobLocator", jobLocator);
        map.put("jobLauncher", jobLauncher);
        map.put(SpringBatchQuartzJob.JOB_NAME, importProductsJob.getName());
        bean.setJobDataAsMap(map);
        bean.setDurability(true);
        return bean;
    }
}
