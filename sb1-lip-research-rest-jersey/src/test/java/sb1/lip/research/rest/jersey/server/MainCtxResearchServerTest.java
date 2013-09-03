package sb1.lip.research.rest.jersey.server;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Vegard S. Bye
 */
public class MainCtxResearchServerTest {

    @Test
    public void testAppCtx() throws Exception {
        new AnnotationConfigApplicationContext(MainCtxResearchServer.class);
    }
}
