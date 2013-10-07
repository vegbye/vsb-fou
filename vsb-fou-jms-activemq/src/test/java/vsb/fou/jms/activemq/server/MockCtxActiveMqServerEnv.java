package vsb.fou.jms.activemq.server;

import org.apache.activemq.broker.BrokerService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

@Configuration
public class MockCtxActiveMqServerEnv {

    @Bean
    public BrokerService broker() {
        return Mockito.mock(BrokerService.class);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return Mockito.mock(ConnectionFactory.class);
    }

}
