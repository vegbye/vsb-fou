package vsb.fou.jms.activemq.server;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvCtxActiveMqServer {

    @Bean(destroyMethod = "stop")
    public PooledConnectionFactory connectionFactory() throws Exception {
        PooledConnectionFactory bean = new PooledConnectionFactory();
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

}
