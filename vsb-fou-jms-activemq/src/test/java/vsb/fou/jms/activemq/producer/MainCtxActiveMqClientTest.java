package vsb.fou.jms.activemq.producer;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainCtxActiveMqClientTest {

    @Test
    public void testAppCtx() throws Exception {
        new AnnotationConfigApplicationContext(MainCtxActiveMqClient.class, MockCtxActiveMqClientEnv.class);
    }
}
