package vsb.fou.jms.activemq.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.TextMessage;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author Vegard S. Bye
 */
@RunWith(MockitoJUnitRunner.class)
public class SyncJmsConsumerTest {

    @InjectMocks
    private SyncJmsConsumer syncJmsConsumer;
    @Mock
    private JmsTemplate jmsTemplate;

    @Test
    public void testIt() {
        TextMessage message = Mockito.mock(TextMessage.class);
        syncJmsConsumer.onMessage(message);

        verify(jmsTemplate).send(anyString(), any(MessageCreator.class));
        verifyNoMoreInteractions(jmsTemplate);
    }
}
