package vsb.fou.batch.spring.productjob;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vsb.fou.batch.spring.common.MainCtxSpringBatchCommon;
import vsb.fou.batch.spring.infra.TestCtxSpringBatch;
import vsb.fou.batch.spring.web.MainCtxSpringBatchWeb;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vegard S. Bye
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainCtxSpringBatchCommon.class, TestCtxSpringBatch.class, MainCtxSpringBatchWeb.class})
public class ImportProductsBatchTest {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job importProductsJob;

    @Test
    public void test_import_products_1() throws Exception {
        JobExecution jobExecution = jobLauncher.run(importProductsJob, new JobParametersBuilder()
                .addString("ID", UUID.randomUUID().toString())
                .toJobParameters());
        assertThat(printBatchFailures(jobExecution), jobExecution.getStatus(), is(BatchStatus.COMPLETED));
        assertThat(jobExecution.getExitStatus(), is(ExitStatus.COMPLETED));
        assertThat(jobExecution.getAllFailureExceptions().size(), is(0));
    }

    @Test
    public void test_import_products_2() throws Exception {
        JobExecution jobExecution = jobLauncher.run(importProductsJob, new JobParametersBuilder()
                .addString("ID", UUID.randomUUID().toString())
                .toJobParameters());
        assertThat(printBatchFailures(jobExecution), jobExecution.getStatus(), is(BatchStatus.COMPLETED));
        assertThat(jobExecution.getExitStatus(), is(ExitStatus.COMPLETED));
        assertThat(jobExecution.getAllFailureExceptions().size(), is(0));
    }

    private String printBatchFailures(JobExecution jobExecution) {
        StringBuilder sb = new StringBuilder();
        for (Throwable t : jobExecution.getAllFailureExceptions()) {
            sb.append(ExceptionUtils.getStackTrace(t));
            sb.append(" ");
        }
        return sb.toString();
    }

}
