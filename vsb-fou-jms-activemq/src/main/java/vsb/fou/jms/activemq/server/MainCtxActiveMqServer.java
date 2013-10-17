package vsb.fou.jms.activemq.server;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import vsb.fou.common.InfraConfig;

@Configuration
@ComponentScan(basePackages = "vsb.fou.jms.activemq.server", excludeFilters = {@ComponentScan.Filter(InfraConfig.class)})
public class MainCtxActiveMqServer {

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer bean = new PropertyPlaceholderConfigurer();
        bean.setLocation(new ClassPathResource("vsb-fou-jms-activemq.properties"));
        return bean;
    }
}
