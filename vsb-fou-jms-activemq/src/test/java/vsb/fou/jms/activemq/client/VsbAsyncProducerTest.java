package vsb.fou.jms.activemq.client;

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
public class VsbAsyncProducerTest {

    @Test
    public void testIt() {
        VsbAsyncProducer vsbAsyncProducer = new VsbAsyncProducer();
        JmsTemplate jmsTemplate = mock(JmsTemplate.class);
        vsbAsyncProducer.setJmsTemplate(jmsTemplate);
        vsbAsyncProducer.doIt("Hei fra JUnit test.");

        verify(jmsTemplate).send(anyString(), any(MessageCreator.class));
        verifyNoMoreInteractions(jmsTemplate);
    }
}
