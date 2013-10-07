package vsb.fou.jms.activemq.client;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainCtxActiveMqClientTest {

    @Test
    public void testAppCtx() throws Exception {
        new AnnotationConfigApplicationContext(MainCtxActiveMqClient.class);
    }
}
