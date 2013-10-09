package vsb.fou.jms.activemq.producer;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vsb.fou.jms.activemq.springtestutils.MockCtxActiveMqClientEnv;

public class MainCtxActiveMqProducerTest {

    @Test
    public void testAppCtx() throws Exception {
        new AnnotationConfigApplicationContext(MainCtxActiveMqProducer.class, MockCtxActiveMqClientEnv.class);
    }
}
