package vsb.fou.jms.activemq.springtestutils;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.broker.util.LoggingBrokerPlugin;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.store.memory.MemoryPersistenceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vsb.fou.common.EnvironmentConfiguration;
import vsb.fou.jms.activemq.common.JmsKonstanter;

import javax.jms.ConnectionFactory;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vegard S. Bye
 */
@Configuration
@EnvironmentConfiguration
public class TestCtxActiveMqEnv {

    @Bean(destroyMethod = "stop")
    public ConnectionFactory connectionFactory() throws Exception {
        PooledConnectionFactory bean = new PooledConnectionFactory();
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(JmsKonstanter.BROKER_URL);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    @Bean(destroyMethod = "stop")
    public BrokerService broker() throws Exception {
        BrokerService bean = new BrokerService();
        bean.setBrokerName(JmsKonstanter.BROKER_NAME);
        List<BrokerPlugin> brokerPlugins = new ArrayList<>();
        LoggingBrokerPlugin loggingBrokerPlugin = new LoggingBrokerPlugin();
        loggingBrokerPlugin.setLogConnectionEvents(true);
        loggingBrokerPlugin.setLogMessageEvents(true);
        loggingBrokerPlugin.setLogTransactionEvents(true);
        loggingBrokerPlugin.setLogProducerEvents(true);
        loggingBrokerPlugin.setLogConsumerEvents(true);
        loggingBrokerPlugin.setLogInternalEvents(false);
        brokerPlugins.add(loggingBrokerPlugin);
        bean.setPlugins(brokerPlugins.toArray(new BrokerPlugin[brokerPlugins.size()]));
        TransportConnector connector = new TransportConnector();
        connector.setUri(new URI(JmsKonstanter.BROKER_URL));
        bean.addConnector(connector);
        bean.setPersistenceAdapter(persistenceAdapter());
        bean.setPersistent(true);
        bean.start();
        return bean;
    }

    @Bean
    public PersistenceAdapter persistenceAdapter() {
        return new MemoryPersistenceAdapter();
    }
}