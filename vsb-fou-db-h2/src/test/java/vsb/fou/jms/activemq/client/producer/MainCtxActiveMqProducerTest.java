package vsb.fou.jms.activemq.client.producer;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vsb.fou.jms.activemq.client.springtestutils.MockCtxActiveMqClientEnv;

public class MainCtxActiveMqProducerTest {

    @Test
    public void testAppCtx() throws Exception {
        new AnnotationConfigApplicationContext(MainCtxActiveMqProducer.class, MockCtxActiveMqClientEnv.class);
    }
}
