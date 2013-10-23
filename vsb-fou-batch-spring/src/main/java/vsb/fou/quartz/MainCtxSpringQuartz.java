package vsb.fou.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan("vsb.fou.quartz")
public class MainCtxSpringQuartz {

    @Resource
    private JobDetail runMeJob;
    @Resource
    private CronTrigger cronTrigger;

    @Bean
    public SchedulerFactoryBean scheduler() throws IOException {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("/quartz.properties"));
        bean.setJobDetails(new JobDetail[]{runMeJob});
        bean.setTriggers(new Trigger[]{cronTrigger});
        bean.setAutoStartup(true);
        bean.setStartupDelay(2);
        return bean;
    }
}
