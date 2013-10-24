package vsb.fou.batch.spring.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import vsb.fou.common.InfraConfig;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Vegard S. Bye
 */
@Configuration
@InfraConfig
public class InfraCtxSpringBatchQuartz {

    @Resource
    private JobDetail importProductsQuartzJob;
    @Resource
    private CronTrigger importProductsQuartzCronTrigger;

    @Bean
    public SchedulerFactoryBean quartzScheduler() throws IOException {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("/quartz.properties"));
        bean.setJobDetails(new JobDetail[]{importProductsQuartzJob});
        bean.setTriggers(new Trigger[]{importProductsQuartzCronTrigger});
        bean.setAutoStartup(true);
        bean.setStartupDelay(2);
        return bean;
    }
}
