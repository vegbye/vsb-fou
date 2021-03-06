package vsb.fou.batch.spring.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vsb.fou.batch.spring.infra.InfraCtxSpringBatch;
import vsb.fou.batch.spring.quartz.InfraCtxSpringBatchQuartz;
import vsb.fou.batch.spring.quartz.MainCtxQuartzScheduler;
import vsb.fou.common.InfraConfig;

/**
 * @author Vegard S. Bye
 */
@Configuration
@Import({
        InfraCtxSpringBatchQuartz.class,
        MainCtxQuartzScheduler.class,
//        MainCtxSpringScheduler.class,
        InfraCtxSpringBatch.class,
        MainCtxSpringBatchWeb.class
})
@InfraConfig
public class AppCtxSpringBatchWeb {
}
