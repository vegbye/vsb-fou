package vsb.fou.batch.spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Vegard S. Bye
 */
public class MainCtxSpringBatchTest {

    @Test
    public void testAppCtx() {
        new AnnotationConfigApplicationContext(MainCtxSpringBatch.class, MockCtxSpringBatch.class);
    }
}
