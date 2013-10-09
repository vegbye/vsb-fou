package vsb.fou.jms.activemq.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author Vegard S. Bye
 */
@RunWith(MockitoJUnitRunner.class)
public class AsyncJmsProducerTest {

    @InjectMocks
    private AsyncJmsProducer asyncJmsProducer;
    @Mock
    private JmsTemplate jmsTemplate;

    @Test
    public void testIt() {
        asyncJmsProducer.doIt("Hei fra JUnit test.");

        verify(jmsTemplate).send(anyString(), any(MessageCreator.class));
        verifyNoMoreInteractions(jmsTemplate);
    }
}
