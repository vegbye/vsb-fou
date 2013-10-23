package vsb.fou.batch.spring.springbatchadmin;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import vsb.fou.common.InfraConfig;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ImportResource({
        "classpath:/META-INF/spring/batch/bootstrap/manager/jmx-context.xml",
        "classpath:/META-INF/spring/batch/bootstrap/integration/*.xml"
})
@InfraConfig
public class InfraSpringBatchAdminCtx {
}
