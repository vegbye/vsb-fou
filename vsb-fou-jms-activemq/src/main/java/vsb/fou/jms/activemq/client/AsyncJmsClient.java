package vsb.fou.jms.activemq.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import vsb.fou.jms.activemq.common.JmsKonstanter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Date;
import java.util.UUID;

public class AsyncJmsClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncJmsClient.class);
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainCtxActiveMqClientEnv.class)) {
            JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);
            AsyncJmsClient asyncJmsClient = new AsyncJmsClient();
            asyncJmsClient.setJmsTemplate(jmsTemplate);
            asyncJmsClient.doIt("Hei fra AsyncJmsClient! " + new Date());
        }
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
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
