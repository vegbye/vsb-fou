package vsb.fou.jms.activemq.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import vsb.fou.jms.activemq.common.JmsKonstanter;

import javax.jms.Message;

public class VsbSyncJmsMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(VsbSyncJmsMain.class);

    public static void main(String[] args) {
        new VsbSyncJmsMain().doIt();
    }

    public void doIt() {
        try {
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainCtxActiveMqClientEnv.class);
            JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);

            ProducerConsumer pc = new ProducerConsumer("synkron jms", JmsKonstanter.SYNC_REQUEST_QUEUE, JmsKonstanter.SYNC_REPLY_QUEUE, jmsTemplate.getDestinationResolver());
            Message message = jmsTemplate.execute(pc, true);
            LOGGER.info("Melding sendt og mottatt med  CorrelationID:" + pc.getCorrelationID() + " msg:" + message);
            ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
