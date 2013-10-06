package vsb.fou.jms.activemq.server;

import org.apache.activemq.broker.BrokerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("vsb.fou.jms.activemq.server")
public class MainCtxActiveMqServer {

    @Bean
    public BrokerService broker() throws Exception {
        BrokerService broker = new BrokerService();
        broker.setBrokerName("vsb-fou-activemq");
        broker.addConnector("tcp://localhost:61616");
        broker.start();
        return broker;
    }
}
