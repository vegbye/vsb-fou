package vsb.fou.batch.spring;

import org.springframework.batch.admin.web.resources.DefaultResourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan("vsb.fou.batch.spring")
public class MainCtxSpringBatch {

    @Bean
    public DefaultResourceService resourceService() {
        DefaultResourceService bean = new DefaultResourceService();
        bean.setServletPath("/springbatchadmin");
        return bean;
    }

}
