package vsb.fou.ws.cxf.client;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan("sb1.lip.research.cxf.client")
@ImportResource("classpath:/cxf-client.xml")
public class MainCtxResearchClient {

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer bean = new PropertyPlaceholderConfigurer();
        bean.setLocation(new ClassPathResource("sb1-lip-research-client.properties"));
        bean.setIgnoreResourceNotFound(false);
        return bean;
    }
}
