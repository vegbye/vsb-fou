package vsb.fou.jms.activemq.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Vegard S. Bye
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainCtxActiveMqClient.class, TestCtxActiveMqClient.class})
public class VsbAsyncProducerNoStubTest {

    @Resource
    private JmsTemplate jmsTemplate;

    @Test
    public void testIt() {
        VsbAsyncProducer vsbAsyncProducer = new VsbAsyncProducer();
        vsbAsyncProducer.setJmsTemplate(jmsTemplate);
        vsbAsyncProducer.doIt("Hei fra JUnit test.");
    }
}
