package vsb.fou.jms.activemq.common;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import vsb.fou.common.EnvironmentConfiguration;

import javax.jms.ConnectionFactory;

@Configuration
@EnvironmentConfiguration
public class MainCtxActiveMqCommonEnv {

    @Value("${vsb-fou-jms-activemq.broker.client.url}")
    private String brokerUrl;

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer bean = new PropertyPlaceholderConfigurer();
        bean.setLocation(new ClassPathResource("vsb-fou-jms-activemq.properties"));
        return bean;
    }

    @Bean(destroyMethod = "stop")
    public ConnectionFactory connectionFactory() throws Exception {
        PooledConnectionFactory bean = new PooledConnectionFactory();
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

}
