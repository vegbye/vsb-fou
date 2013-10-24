package vsb.fou.batch.spring.quartz;

import org.quartz.CronTrigger;
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
    private JobDetail vsbQuartzJob;

    @Bean
    public CronTriggerFactoryBean quartzCronTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(vsbQuartzJob);
        bean.setCronExpression("0/10 * * * * ?");
        bean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
        return bean;
    }

}