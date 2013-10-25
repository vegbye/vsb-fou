package vsb.fou.batch.spring.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(InfraCtxSpringBatchQuartz.class);
    @Resource(name = "importProductsQuartzJob")
    private JobDetail importProductsQuartzJob;
    @Resource(name = "importProductsQuartzCronTrigger")
    private CronTrigger importProductsQuartzCronTrigger;
    @Resource(name = "helloQuartzJob")
    private JobDetail helloQuartzJob;
    @Resource(name = "helloQuartzCronTrigger")
    private CronTrigger helloQuartzCronTrigger;
    @Resource(name = "helloQuartzJob")
    private JobDetail heiQuartzJobb;
    @Resource(name = "heiQuartzCronTrigger")
    private CronTrigger heiQuartzCronTrigger;

    @Bean
    public SchedulerFactoryBean quartzScheduler() throws IOException {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        ClassPathResource resource = new ClassPathResource("/quartz.properties");
        LOGGER.info("Bruker quartz konfig fil:" + resource.getURL());
        bean.setConfigLocation(resource);
        bean.setJobDetails(new JobDetail[]{/*importProductsQuartzJob, */helloQuartzJob, heiQuartzJobb});
        bean.setTriggers(new Trigger[]{/*importProductsQuartzCronTrigger, */helloQuartzCronTrigger, heiQuartzCronTrigger});
        bean.setAutoStartup(true);
        bean.setStartupDelay(2);
        return bean;
    }
}
