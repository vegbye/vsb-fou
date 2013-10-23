package vsb.fou.batch.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vsb.fou.common.InfraConfig;
import vsb.fou.quartz.MainCtxSpringQuartz;

/**
 * @author Vegard S. Bye
 */
@Configuration
@Import({InfraCtxSpringBatch.class, MainCtxSpringBatch.class, SpringBatchAdminCtx.class, MainCtxSpringQuartz.class})
@InfraConfig
public class AppCtxSpringBatch {
}
