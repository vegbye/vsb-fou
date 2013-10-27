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
    private JobDetail importProductsQuartzJob;
    @Resource
    private JobDetail helloQuartzJob;
    @Resource
    private JobDetail heiQuartzJobb;

    @Bean
    public CronTriggerFactoryBean heiQuartzCronTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(heiQuartzJobb);
        bean.setCronExpression("0/9 * * * * ?");
        bean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean helloQuartzCronTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(helloQuartzJob);
        bean.setCronExpression("0/4 * * * * ?");
        bean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean importProductsQuartzCronTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(importProductsQuartzJob);
        bean.setCronExpression("0/23 * * * * ?");
        bean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
        return bean;
    }

}
