package vsb.fou.jms.activemq.producer;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

@Configuration
public class MockCtxActiveMqClientEnv {

    @Bean
    public ConnectionFactory connectionFactory() {
        return Mockito.mock(ConnectionFactory.class);
    }

}
