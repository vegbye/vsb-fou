package vsb.fou.jms.activemq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import vsb.fou.jms.activemq.common.JmsKonstanter;

import javax.jms.Message;

public class SyncJmsClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncJmsClient.class);
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainCtxActiveMqClientEnv.class)) {
            SyncJmsClient syncJmsClient = new SyncJmsClient();
            JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);
            syncJmsClient.setJmsTemplate(jmsTemplate);
            syncJmsClient.doIt("synkron jms");
        }
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Message doIt(String msg) {
        try {
            ProducerConsumer pc = new ProducerConsumer(msg, JmsKonstanter.SYNC_REQUEST_QUEUE, JmsKonstanter.SYNC_REPLY_QUEUE, jmsTemplate.getDestinationResolver());
            Message message = jmsTemplate.execute(pc, true);
            LOGGER.info("Melding sendt og mottatt med CorrelationID:" + pc.getCorrelationID() + " msg:" + message);
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
