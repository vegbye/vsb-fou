package vsb.fou.jms.activemq.client.consumer;

import org.junit.Test;
import org.mockito.Mockito;

import javax.jms.Message;

/**
 * @author Vegard S. Bye
 */
public class AsyncJmsConsumerTest {

    @Test
    public void testIt() {
        Message message = Mockito.mock(Message.class);
        AsyncJmsConsumer asyncJmsConsumer = new AsyncJmsConsumer();
        asyncJmsConsumer.onMessage(message);
    }
}
