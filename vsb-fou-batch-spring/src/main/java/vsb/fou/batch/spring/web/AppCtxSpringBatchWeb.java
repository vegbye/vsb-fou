package vsb.fou.batch.spring.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vsb.fou.batch.spring.infra.InfraCtxSpringBatch;
import vsb.fou.batch.spring.scheduler.MainCtxSpringScheduler;
import vsb.fou.batch.spring.springbatchadmin.MainCtxSpringBatchAdmin;
import vsb.fou.common.InfraConfig;

/**
 * @author Vegard S. Bye
 */
@Configuration
@Import({
        //InfraCtxSpringBatchQuartz.class,
        //MainCtxQuartzScheduler.class,
        MainCtxSpringScheduler.class,
        InfraCtxSpringBatch.class,
        MainCtxSpringBatchWeb.class,
        MainCtxSpringBatchAdmin.class
})
@InfraConfig
public class AppCtxSpringBatchWeb {
}
