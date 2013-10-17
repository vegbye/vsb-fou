package vsb.fou.batch.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vsb.fou.common.InfraConfig;

/**
 * @author Vegard S. Bye
 */
@Configuration
@Import({InfraCtxSpringBatch.class, MainCtxSpringBatch.class})
@InfraConfig
public class AppCtxSpringBatch {
}
