package vsb.fou.jms.activemq.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import vsb.fou.common.EnvironmentConfiguration;
import vsb.fou.jms.activemq.common.JmsKonstanter;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;

@Configuration
@ComponentScan(basePackages = "vsb.fou.jms.activemq.server", excludeFilters = {@ComponentScan.Filter(EnvironmentConfiguration.class)})
public class MainCtxActiveMqServer {

    @Resource
    private ConnectionFactory connectionFactory;
    @Resource
    private VsbJmsListener vsbJmsListener;
    @Resource
    private DialogueJmsListener dialogueJmsListener;

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate bean = new JmsTemplate();
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    @Bean
    public DefaultMessageListenerContainer container() {
        DefaultMessageListenerContainer bean = new DefaultMessageListenerContainer();
        bean.setConnectionFactory(connectionFactory);
        bean.setDestinationName(JmsKonstanter.ASYNC_REQUEST_QUEUE);
        bean.setDestinationResolver(dynamicDestinationResolver());
        bean.setAutoStartup(true);
        bean.setMessageListener(vsbJmsListener);
        return bean;
    }

    @Bean
    public DefaultMessageListenerContainer dialogueContainer() {
        DefaultMessageListenerContainer bean = new DefaultMessageListenerContainer();
        bean.setConnectionFactory(connectionFactory);
        bean.setDestinationName(JmsKonstanter.SYNC_REQUEST_QUEUE);
        bean.setDestinationResolver(dynamicDestinationResolver());
        bean.setAutoStartup(true);
        bean.setMessageListener(dialogueJmsListener);
        return bean;
    }

    @Bean
    public DynamicDestinationResolver dynamicDestinationResolver() {
        return new DynamicDestinationResolver();
    }

}
