package vsb.fou.jms.activemq.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import vsb.fou.common.EnvironmentConfiguration;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;

@Configuration
@ComponentScan(basePackages = "vsb.fou.jms.activemq.producer", excludeFilters = {@ComponentScan.Filter(EnvironmentConfiguration.class)})
public class MainCtxActiveMqProducer {

    @Resource
    public ConnectionFactory connectionFactory;

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate bean = new JmsTemplate();
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }
}
