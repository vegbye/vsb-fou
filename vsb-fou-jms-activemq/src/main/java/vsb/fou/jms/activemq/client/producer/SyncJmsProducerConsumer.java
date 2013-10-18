package vsb.fou.jms.activemq.client.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.stereotype.Service;
import vsb.fou.jms.activemq.client.common.JmsKonstanter;
import vsb.fou.jms.activemq.client.common.JmsMessageUtil;
import vsb.fou.jms.activemq.client.common.MainCtxActiveMqClientEnv;
import vsb.fou.jms.activemq.client.consumer.MainCtxActiveMqConsumer;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import java.util.Date;
import java.util.UUID;

@Service
public class SyncJmsProducerConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncJmsProducerConsumer.class);
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        Class[] ctxClasses = {MainCtxActiveMqProducer.class, MainCtxActiveMqConsumer.class, MainCtxActiveMqClientEnv.class};
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ctxClasses)) {
            SyncJmsProducerConsumer syncJmsProducerConsumer = ctx.getBean(SyncJmsProducerConsumer.class);
            syncJmsProducerConsumer.doIt("synkron jms");
        }
    }

    public Message doIt(final String msg) {
        try {
            final String correlationID = UUID.randomUUID().toString();
            final DestinationResolver destinationResolver = jmsTemplate.getDestinationResolver();
            Message message = jmsTemplate.execute(new SessionCallback<Message>() {
                @Override
                public Message doInJms(Session session) throws JMSException {
                    MessageConsumer consumer = null;
                    MessageProducer producer = null;
                    try {
                        final Destination requestQueueDest = destinationResolver.resolveDestinationName(session, JmsKonstanter.SYNC_REQUEST_QUEUE, false);
                        final Destination replyQueueDest = destinationResolver.resolveDestinationName(session, JmsKonstanter.SYNC_REPLY_QUEUE, false);
                        String messageSelector = JmsMessageUtil.getCorrelationIDMessageSelector(correlationID);
                        consumer = session.createConsumer(replyQueueDest, messageSelector);
                        final Message replyMsg = session.createTextMessage("Hei hei:" + msg + " " + new Date());
                        replyMsg.setJMSCorrelationID(correlationID);
                        replyMsg.setJMSMessageID(Long.toString(System.currentTimeMillis()));
                        replyMsg.setJMSReplyTo(replyQueueDest);

                        producer = session.createProducer(requestQueueDest);
                        int timeToLive = 5 * 60 * 1000;
                        producer.setTimeToLive(timeToLive + 1000L);
                        LOGGER.info("Sending a replyMsg on " + JmsKonstanter.SYNC_REQUEST_QUEUE + " expecting answer on " + JmsKonstanter.SYNC_REPLY_QUEUE + " using selector: " + consumer.getMessageSelector() + " with timeout " + timeToLive);
                        producer.send(replyMsg);
                        return consumer.receive(timeToLive);
                    } finally {
                        JmsUtils.closeMessageConsumer(consumer);
                        JmsUtils.closeMessageProducer(producer);
                    }
                }
            }, true);
            LOGGER.info("Melding sendt og mottatt med CorrelationID:" + correlationID + " msg:" + message);
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
