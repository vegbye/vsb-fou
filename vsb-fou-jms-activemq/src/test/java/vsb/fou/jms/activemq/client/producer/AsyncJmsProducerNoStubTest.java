package vsb.fou.jms.activemq.client.producer;

import org.apache.activemq.broker.BrokerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vsb.fou.jms.activemq.client.consumer.MainCtxActiveMqConsumer;
import vsb.fou.jms.activemq.client.springtestutils.TestCtxActiveMqServerEnv;

/**
 * @author Vegard S. Bye
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainCtxActiveMqProducer.class, MainCtxActiveMqConsumer.class, TestCtxActiveMqServerEnv.class})
public class AsyncJmsProducerNoStubTest {

    @Autowired
    public BrokerService broker;
    @Autowired
    private AsyncJmsProducer asyncJmsProducer;

    @Test
    public void testIt() {
        asyncJmsProducer.doIt("Hei fra JUnit test.");
    }
}
