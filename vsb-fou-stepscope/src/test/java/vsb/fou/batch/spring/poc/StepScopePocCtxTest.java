package vsb.fou.batch.spring.poc;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Vegard S. Bye
 */
public class StepScopePocCtxTest {

    @Test
    @Ignore
    public void testAppCtx() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(StepScopePocCtx.class);

        Object stepScopedReader = ctx.getBean("stepScopedReader");
        System.out.println("stepScopedReader = " + stepScopedReader);
    }
}
