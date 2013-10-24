package vsb.fou.batch.spring.web;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vsb.fou.batch.spring.infra.MockCtxSpringBatch;
import vsb.fou.batch.spring.infra.TestCtxSpringBatchQuartz;
import vsb.fou.batch.spring.job.MainCtxImportProductJob;
import vsb.fou.batch.spring.quartz.MainCtxSpringBatchQuartz;

/**
 * @author Vegard S. Bye
 */
public class MainCtxSpringBatchWebTest {

    @Test
    public void testAppCtx() {
        new AnnotationConfigApplicationContext(
                MockCtxSpringBatch.class,
                MainCtxSpringBatchQuartz.class,
                TestCtxSpringBatchQuartz.class,
                MainCtxImportProductJob.class,
                MainCtxSpringBatchWeb.class);
    }
}
