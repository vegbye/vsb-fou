package vsb.fou.rest.jersey.client;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import vsb.fou.rest.jersey.common.VsbLoggingFilter;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan("vsb.fou.rest.jersey.client")
public class MainCtxResearchClient {

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer bean = new PropertyPlaceholderConfigurer();
        bean.setLocation(new ClassPathResource("vsb-fou-client.properties"));
        bean.setIgnoreResourceNotFound(false);
        return bean;
    }

    @Bean
    public Client restClient() {
        return ClientBuilder.newClient().register(new JacksonFeature()).register(new VsbLoggingFilter());
    }

    @PostConstruct
    public void bridgeJulToSlf4j() {
        org.slf4j.bridge.SLF4JBridgeHandler.removeHandlersForRootLogger();
        org.slf4j.bridge.SLF4JBridgeHandler.install();
    }
}
