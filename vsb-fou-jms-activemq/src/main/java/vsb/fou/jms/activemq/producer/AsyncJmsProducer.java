package vsb.fou.jms.activemq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import vsb.fou.jms.activemq.common.JmsKonstanter;
import vsb.fou.jms.activemq.common.MainCtxActiveMqCommonEnv;
import vsb.fou.jms.activemq.consumer.MainCtxActiveMqConsumer;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Date;
import java.util.UUID;

@Service
public class AsyncJmsProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncJmsProducer.class);
    @Resource
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        Class[] ctxClasses = {MainCtxActiveMqProducer.class, MainCtxActiveMqConsumer.class, MainCtxActiveMqCommonEnv.class};
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ctxClasses)) {
            AsyncJmsProducer asyncJmsProducer = ctx.getBean(AsyncJmsProducer.class);
            asyncJmsProducer.doIt("Hei fra AsyncJmsProducer! " + new Date());
        }
    }

    public void doIt(final String msg) {
        try {
            final String correlationID = UUID.randomUUID().toString();
            jmsTemplate.send(JmsKonstanter.ASYNC_REQUEST_QUEUE, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage(msg);
                    message.setJMSCorrelationID(correlationID);
                    return message;
                }
            });
            LOGGER.info("Melding sendt med CorrelationID:" + correlationID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
