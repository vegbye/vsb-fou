package vsb.fou.jms.activemq.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;

@Configuration
@ComponentScan("vsb.fou.jms.activemq.producer")
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
