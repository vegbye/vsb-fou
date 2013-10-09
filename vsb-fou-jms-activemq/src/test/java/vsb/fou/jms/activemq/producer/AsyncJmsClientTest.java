package vsb.fou.jms.activemq.producer;

import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author Vegard S. Bye
 */
public class AsyncJmsClientTest {

    @Test
    public void testIt() {
        AsyncJmsClient asyncJmsClient = new AsyncJmsClient();
        JmsTemplate jmsTemplate = mock(JmsTemplate.class);
        asyncJmsClient.setJmsTemplate(jmsTemplate);
        asyncJmsClient.doIt("Hei fra JUnit test.");

        verify(jmsTemplate).send(anyString(), any(MessageCreator.class));
        verifyNoMoreInteractions(jmsTemplate);
    }
}
