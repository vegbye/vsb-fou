package vsb.fou.batch.spring.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vsb.fou.batch.spring.job.MainCtxSpringBatchJobs;
import vsb.fou.common.InfraConfig;
import vsb.fou.quartz.MainCtxSpringQuartz;

/**
 * @author Vegard S. Bye
 */
@Configuration
@Import({InfraCtxSpringBatch.class, MainCtxSpringBatchJobs.class, MainCtxSpringBatch.class, MainCtxSpringQuartz.class, SpringBatchAdminCtx.class})
@InfraConfig
public class AppCtxSpringBatch {
}
