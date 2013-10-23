package vsb.fou.batch.spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vsb.fou.batch.spring.web.MainCtxSpringBatch;

/**
 * @author Vegard S. Bye
 */
public class MainCtxSpringBatchTest {

    @Test
    public void testAppCtx() {
        new AnnotationConfigApplicationContext(
                MockCtxSpringBatch.class,
                MainCtxSpringBatch.class);
    }
}
