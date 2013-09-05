package vsb.fou.ws.cxf.client;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Vegard S. Bye
 */
public class MainCtxResearchClientTest {

    @Test
    public void testAppCtx() throws Exception {
        new AnnotationConfigApplicationContext(MainCtxResearchClient.class);
    }

}
