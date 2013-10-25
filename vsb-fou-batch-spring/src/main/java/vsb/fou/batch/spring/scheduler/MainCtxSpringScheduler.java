package vsb.fou.batch.spring.scheduler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan("vsb.fou.batch.spring.scheduler")
@ImportResource("classpath:/spring-scheduler.xml")
public class MainCtxSpringScheduler {
}
