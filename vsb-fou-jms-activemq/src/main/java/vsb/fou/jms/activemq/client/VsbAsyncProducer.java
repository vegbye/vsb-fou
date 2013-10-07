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

public class VsbAsyncProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(VsbAsyncProducer.class);

    public static void main(String[] args) {
        new VsbAsyncProducer().doIt();
    }

    public void doIt() {
        try {
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainCtxActiveMqClient.class);
            JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);

            final String correlationID = UUID.randomUUID().toString();
            jmsTemplate.send(JmsKonstanter.ASYNC_REQUEST_QUEUE, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage("Hei fra VsbAsyncProducer! " + new Date());
                    message.setJMSCorrelationID(correlationID);
                    return message;
                }
            });
            LOGGER.info("Melding sendt med CorrelationID:" + correlationID);
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
