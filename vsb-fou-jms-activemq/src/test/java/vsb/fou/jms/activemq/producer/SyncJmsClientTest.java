package vsb.fou.jms.activemq.producer;

import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author Vegard S. Bye
 */
public class SyncJmsClientTest {

    @Test
    public void testIt() throws Exception {
        SyncJmsClient syncJmsClient = new SyncJmsClient();
        JmsTemplate jmsTemplate = mock(JmsTemplate.class);
        syncJmsClient.setJmsTemplate(jmsTemplate);
        syncJmsClient.doIt("Hei fra JUnit test.");

        verify(jmsTemplate).execute(any(ProducerConsumer.class), anyBoolean());
        verify(jmsTemplate).getDestinationResolver();
        verifyNoMoreInteractions(jmsTemplate);
    }
}
