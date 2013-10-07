package vsb.fou.jms.activemq.server;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainCtxActiveMqServerTest {

    @Test
    public void testAppCtx() throws Exception {
        new AnnotationConfigApplicationContext(MainCtxActiveMqServer.class);
    }
}
