package vsb.fou.jms.activemq.client.producer;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vsb.fou.jms.activemq.client.consumer.MainCtxActiveMqConsumer;
import vsb.fou.jms.activemq.client.springtestutils.TestCtxActiveMqServerEnv;

import javax.jms.Message;
import javax.jms.TextMessage;

import static org.junit.Assert.assertThat;

/**
 * @author Vegard S. Bye
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainCtxActiveMqProducer.class, MainCtxActiveMqConsumer.class, TestCtxActiveMqServerEnv.class})
public class SyncJmsProducerConsumerNoStubTest {

    @Autowired
    private SyncJmsProducerConsumer syncJmsProducerConsumer;

    @Test
    public void testIt() throws Exception {
        String msg = "Hei fra JUnit test:" + System.currentTimeMillis();
        Message message = syncJmsProducerConsumer.doIt(msg);
        assertThat(message, CoreMatchers.notNullValue());
        assertThat(message, CoreMatchers.instanceOf(TextMessage.class));
        TextMessage textMessage = (TextMessage) message;
        assertThat(textMessage.getText(), CoreMatchers.containsString(msg));
    }
}
