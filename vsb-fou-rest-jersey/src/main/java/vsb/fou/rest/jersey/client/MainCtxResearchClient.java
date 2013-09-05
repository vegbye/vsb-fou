package vsb.fou.rest.jersey.client;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan("vsb.fou.rest.jersey.client")
public class MainCtxResearchClient {

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer bean = new PropertyPlaceholderConfigurer();
        bean.setLocation(new ClassPathResource("sb1-lip-research-client.properties"));
        bean.setIgnoreResourceNotFound(false);
        return bean;
    }
}
