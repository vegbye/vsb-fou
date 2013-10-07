package vsb.fou.jms.activemq.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.support.destination.DestinationResolver;
import vsb.fou.jms.activemq.common.JmsMessageUtil;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import java.util.Date;
import java.util.UUID;

/**
 * @author Vegard S. Bye
 */
public class ProducerConsumer implements SessionCallback<Message> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerConsumer.class);
    private final String requestMsg;
    private final DestinationResolver destinationResolver;
    private final String requestQueue;
    private final String replyQueue;
    private String correlationID;

    public String getCorrelationID() {
        return correlationID;
    }

    public ProducerConsumer(String requestMsg, String requestQueue, String replyQueue, final DestinationResolver destinationResolver) {
        this.requestMsg = requestMsg;
        this.requestQueue = requestQueue;
        this.replyQueue = replyQueue;
        this.destinationResolver = destinationResolver;
    }

    @Override
    public Message doInJms(Session session) throws JMSException {
        MessageConsumer consumer = null;
        MessageProducer producer = null;
        try {
            correlationID = UUID.randomUUID().toString();
            final Destination requestQueueDest = destinationResolver.resolveDestinationName(session, requestQueue, false);
            final Destination replyQueueDest = destinationResolver.resolveDestinationName(session, replyQueue, false);
            String messageSelector = JmsMessageUtil.getCorrelationIDMessageSelector(correlationID);
            consumer = session.createConsumer(replyQueueDest, messageSelector);
            final Message replyMsg = session.createTextMessage("Hei hei:" + requestMsg + " " + new Date());
            replyMsg.setJMSCorrelationID(correlationID);
            replyMsg.setJMSMessageID(Long.toString(System.currentTimeMillis()));
            replyMsg.setJMSReplyTo(replyQueueDest);

            producer = session.createProducer(requestQueueDest);
            int timeToLive = 5 * 60 * 1000;
            producer.setTimeToLive(timeToLive + 1000L);
            LOGGER.info("Sending a replyMsg on " + requestQueue + " expecting answer on " + replyQueue + " using selector: " + consumer.getMessageSelector() + " with timeout " + timeToLive);
            producer.send(replyMsg);
            return consumer.receive(timeToLive);
        } finally {
            JmsUtils.closeMessageConsumer(consumer);
            JmsUtils.closeMessageProducer(producer);
        }
    }
}
