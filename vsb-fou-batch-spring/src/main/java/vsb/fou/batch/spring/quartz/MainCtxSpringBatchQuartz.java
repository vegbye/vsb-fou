package vsb.fou.batch.spring.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan("vsb.fou.batch.spring.quartz")
@Import({JobDetailCtx.class, CronTriggerCtx.class})
public class MainCtxSpringBatchQuartz {

    @Resource
    private JobDetail springBatchQuartzJob;
    @Resource
    private CronTrigger quartzCronTrigger;

    @Bean
    public SchedulerFactoryBean quartzScheduler() throws IOException {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("/quartz.properties"));
        bean.setJobDetails(new JobDetail[]{springBatchQuartzJob});
        bean.setTriggers(new Trigger[]{quartzCronTrigger});
        bean.setAutoStartup(true);
        bean.setStartupDelay(2);
        return bean;
    }
}
