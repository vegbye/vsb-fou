package vsb.fou.jms.activemq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author Vegard S. Bye
 */
@Service
public class AsyncJmsConsumer implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncJmsConsumer.class);

    @Override
    public void onMessage(Message message) {
        try {
            LOGGER.info("Melding mottatt med CorrelationID:" + message.getJMSCorrelationID());
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
