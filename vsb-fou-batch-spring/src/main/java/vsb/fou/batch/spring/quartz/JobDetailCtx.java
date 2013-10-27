package vsb.fou.batch.spring.quartz;

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
    @Resource
    private JobLauncher syncJobLauncher;

    @Bean
    public JobDetailFactoryBean heiQuartzJobb() {
        return getJobDetailFactoryBean("heiJobb", false);
    }

    @Bean
    public JobDetailFactoryBean helloQuartzJob() {
        return getJobDetailFactoryBean("helloJob", true);
    }

    @Bean
    public JobDetailFactoryBean importProductsQuartzJob() {
        return getJobDetailFactoryBean("importProductsJob", false);
    }

    private JobDetailFactoryBean getJobDetailFactoryBean(String jobName, boolean smoketest) {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(SpringBatchQuartzJob.class);
        Map<String, Object> map = new HashMap<>();
        map.put("jobLocator", jobLocator);
        map.put("jobLauncher", jobLauncher);
        map.put("syncJobLauncher", syncJobLauncher);
        map.put(SpringBatchQuartzJob.JOB_NAME, jobName);
        if (smoketest) {
            map.put("smoketest", "true");
        } else {
            map.put("smoketest", "false");
        }
        bean.setJobDataAsMap(map);
        bean.setDurability(true);
        return bean;
    }
}
