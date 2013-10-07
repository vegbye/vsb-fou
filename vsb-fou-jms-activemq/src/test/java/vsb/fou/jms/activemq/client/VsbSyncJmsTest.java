package vsb.fou.jms.activemq.client;

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
public class VsbSyncJmsTest {

    @Test
    public void testIt() throws Exception {
        VsbSyncJms vsbSyncJms = new VsbSyncJms();
        JmsTemplate jmsTemplate = mock(JmsTemplate.class);
        vsbSyncJms.setJmsTemplate(jmsTemplate);
        vsbSyncJms.doIt("Hei fra JUnit test.");

        verify(jmsTemplate).execute(any(ProducerConsumer.class), anyBoolean());
        verify(jmsTemplate).getDestinationResolver();
        verifyNoMoreInteractions(jmsTemplate);
    }
}
