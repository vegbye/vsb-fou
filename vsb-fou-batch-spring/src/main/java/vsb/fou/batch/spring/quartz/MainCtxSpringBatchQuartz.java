package vsb.fou.batch.spring.quartz;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan("vsb.fou.batch.spring.quartz")
@Import({JobDetailCtx.class, CronTriggerCtx.class})
public class MainCtxSpringBatchQuartz {
}
