package vsb.fou.batch.spring.quartz;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vsb.fou.common.InfraConfig;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan(basePackages = "vsb.fou.batch.spring.quartz", excludeFilters = {@ComponentScan.Filter(InfraConfig.class)})
@Import({JobDetailCtx.class, CronTriggerCtx.class})
public class MainCtxSpringBatchQuartz {
}
