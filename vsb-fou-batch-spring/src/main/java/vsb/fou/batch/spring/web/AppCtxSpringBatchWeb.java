package vsb.fou.batch.spring.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vsb.fou.batch.spring.infra.InfraCtxSpringBatch;
import vsb.fou.batch.spring.job.MainCtxImportProductJob;
import vsb.fou.batch.spring.springbatchadmin.InfraSpringBatchAdminCtx;
import vsb.fou.batch.spring.springbatchadmin.MainSpringBatchAdminCtx;
import vsb.fou.common.InfraConfig;
import vsb.fou.quartz.MainCtxSpringQuartz;

/**
 * @author Vegard S. Bye
 */
@Configuration
@Import({
        MainCtxImportProductJob.class,
        InfraCtxSpringBatch.class,
        MainCtxSpringBatchWeb.class,
        MainCtxSpringQuartz.class,
        InfraSpringBatchAdminCtx.class,
        MainSpringBatchAdminCtx.class
})
@InfraConfig
public class AppCtxSpringBatchWeb {
}
