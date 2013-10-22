package vsb.fou.quartz;

import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import javax.annotation.Resource;

/**
 * @author Vegard S. Bye
 */
@Configuration
public class CronTriggerCtx {

    @Resource
    private JobDetail runMeJob;

    @Bean
    public CronTriggerFactoryBean cronTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(runMeJob);
        bean.setCronExpression("0/5 * * * * ?");
        return bean;
    }

}
