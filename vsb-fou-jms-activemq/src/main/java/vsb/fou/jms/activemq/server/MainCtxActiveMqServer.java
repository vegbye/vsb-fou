package vsb.fou.jms.activemq.server;

import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.broker.util.LoggingBrokerPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import vsb.fou.jms.activemq.common.JmsKonstanter;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("vsb.fou.jms.activemq.server")
@Import(EnvCtxActiveMqServer.class)
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

    @Bean(destroyMethod = "stop")
    public BrokerService broker() throws Exception {
        BrokerService bean = new BrokerService();
        bean.setBrokerName("vsb-fou");
        List<BrokerPlugin> brokerPlugins = new ArrayList<>();
        LoggingBrokerPlugin loggingBrokerPlugin = new LoggingBrokerPlugin();
        loggingBrokerPlugin.setLogAll(true);
        brokerPlugins.add(loggingBrokerPlugin);
        bean.setPlugins(brokerPlugins.toArray(new BrokerPlugin[brokerPlugins.size()]));
        TransportConnector connector = new TransportConnector();
        connector.setUri(new URI("tcp://localhost:61616"));
        bean.addConnector(connector);
        bean.setPersistent(true);
        bean.start();
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
