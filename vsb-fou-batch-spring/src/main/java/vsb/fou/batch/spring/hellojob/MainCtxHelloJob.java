package vsb.fou.batch.spring.hellojob;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan("vsb.fou.batch.spring.hellojob")
@ImportResource("classpath:/hello-job.xml")
public class MainCtxHelloJob {
}
