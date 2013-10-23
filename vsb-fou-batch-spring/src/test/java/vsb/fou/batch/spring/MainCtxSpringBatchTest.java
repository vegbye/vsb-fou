package vsb.fou.batch.spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vsb.fou.batch.spring.web.MainCtxSpringBatchWeb;

/**
 * @author Vegard S. Bye
 */
public class MainCtxSpringBatchTest {

    @Test
    public void testAppCtx() {
        new AnnotationConfigApplicationContext(
                MockCtxSpringBatch.class,
                MainCtxSpringBatchWeb.class);
    }
}
