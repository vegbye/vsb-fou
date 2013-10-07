package vsb.fou.jms.activemq.server;

import org.junit.Test;
import org.mockito.Mockito;

import javax.jms.Message;

/**
 * @author Vegard S. Bye
 */
public class VsbJmsListenerTest {

    @Test
    public void testIt() {
        Message message = Mockito.mock(Message.class);
        VsbJmsListener vsbJmsListener = new VsbJmsListener();
        vsbJmsListener.onMessage(message);
    }
}
