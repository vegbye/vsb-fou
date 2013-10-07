package vsb.fou.jms.activemq.server;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.broker.util.LoggingBrokerPlugin;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.store.kahadb.KahaDBPersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vsb.fou.common.EnvironmentConfiguration;
import vsb.fou.jms.activemq.common.JmsKonstanter;

import javax.jms.ConnectionFactory;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Import(MainCtxActiveMqServer.class)
@EnvironmentConfiguration
public class MainCtxActiveMqServerEnv {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainCtxActiveMqServerEnv.class);

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
        bean.setPersistent(true);
        bean.setPersistenceAdapter(persistenceAdapter());
        bean.start();
        return bean;
    }

    @Bean
    public PersistenceAdapter persistenceAdapter() throws IOException {
        KahaDBPersistenceAdapter kahaDBPersistenceAdapter = new KahaDBPersistenceAdapter();
        File kahaDir = new File(System.getProperty("java.io.tmpdir"), "vsb-fou-kahadb");
        LOGGER.info("KahaDB katalog:" + kahaDir.getAbsolutePath());
        if (!kahaDir.exists()) {
            boolean mkdir = kahaDir.mkdir();
            if (!mkdir) {
                throw new RuntimeException("Fikk ikke opprettet KahaDB katalog:" + kahaDir.getAbsolutePath());
            }
        }
        kahaDBPersistenceAdapter.setDirectory(kahaDir);
        return kahaDBPersistenceAdapter;
    }

    @Bean(destroyMethod = "stop")
    public ConnectionFactory connectionFactory() throws Exception {
        PooledConnectionFactory bean = new PooledConnectionFactory();
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(JmsKonstanter.BROKER_URL);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

}
