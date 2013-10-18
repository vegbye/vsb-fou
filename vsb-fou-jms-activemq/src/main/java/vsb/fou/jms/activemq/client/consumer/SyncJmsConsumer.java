package vsb.fou.jms.activemq.client.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import vsb.fou.jms.activemq.client.common.JmsKonstanter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Date;

/**
 * @author Vegard S. Bye
 */
@Service
public class SyncJmsConsumer implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncJmsConsumer.class);
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * Dette tilsvarer det som Dialogue gjør.
     */
    @Override
    public void onMessage(Message message) {
        try {
            final String correlationID = message.getJMSCorrelationID();
            LOGGER.info("Melding mottatt med CorrelationID:" + correlationID);
            final TextMessage requestTextMessage = (TextMessage) message;
            jmsTemplate.send(JmsKonstanter.SYNC_REPLY_QUEUE, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage textMessage = session.createTextMessage("Hei fra DialogueJmsListener! " + new Date() + " " + requestTextMessage.getText());
                    textMessage.setJMSCorrelationID(correlationID);
                    textMessage.setJMSMessageID(Long.toString(System.currentTimeMillis()));
                    return textMessage;
                }
            });
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
