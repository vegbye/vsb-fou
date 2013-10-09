package vsb.fou.jms.activemq.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author Vegard S. Bye
 */
@RunWith(MockitoJUnitRunner.class)
public class SyncJmsProducerConsumerTest {

    @InjectMocks
    private SyncJmsProducerConsumer syncJmsProducerConsumer;
    @Mock
    private JmsTemplate jmsTemplate;

    @Test
    public void testIt() throws Exception {
        syncJmsProducerConsumer.doIt("Hei fra JUnit test.");

        verify(jmsTemplate).execute(any(SessionCallback.class), anyBoolean());
        verify(jmsTemplate).getDestinationResolver();
        verifyNoMoreInteractions(jmsTemplate);
    }
}
