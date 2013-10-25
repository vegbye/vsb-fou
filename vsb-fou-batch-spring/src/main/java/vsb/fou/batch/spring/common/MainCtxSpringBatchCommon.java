package vsb.fou.batch.spring.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan("vsb.fou.batch.spring.common")
@ImportResource("classpath:/hei-jobb.xml")
public class MainCtxSpringBatchCommon {
}
