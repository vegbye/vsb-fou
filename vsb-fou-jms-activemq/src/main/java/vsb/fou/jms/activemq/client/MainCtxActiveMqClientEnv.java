package vsb.fou.jms.activemq.client;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vsb.fou.common.EnvironmentConfiguration;
import vsb.fou.jms.activemq.common.JmsKonstanter;

import javax.jms.ConnectionFactory;

@Configuration
@Import(MainCtxActiveMqClient.class)
@EnvironmentConfiguration
public class MainCtxActiveMqClientEnv {

    @Bean(destroyMethod = "stop")
    public ConnectionFactory connectionFactory() throws Exception {
        PooledConnectionFactory bean = new PooledConnectionFactory();
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(JmsKonstanter.BROKER_URL);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

}
