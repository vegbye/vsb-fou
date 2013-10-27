package vsb.fou.batch.spring.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vsb.fou.batch.spring.common.VsbJobParametersIncrementer;

import javax.annotation.Resource;

/**
 * @author Vegard S. Bye
 */
@Component
public class SpringBatchLauncher {

    @Resource(name = "importProductsJob")
    private Job importProductsJob;
    @Resource(name = "helloJob")
    private Job helloJob;
    @Resource(name = "heiJobb")
    private Job heiJobb;
    @Autowired
    private JobLauncher jobLauncher;

    public void launchImportProductsJob() throws Exception {
        JobParameters jobParams = createJobParameters();
        jobLauncher.run(importProductsJob, jobParams);
    }

    public void launchHelloJob() throws Exception {
        JobParameters jobParams = createJobParameters();
        jobLauncher.run(helloJob, jobParams);
    }

    public void launchHeiJobb() throws Exception {
        JobParameters jobParams = createJobParameters();
        jobLauncher.run(heiJobb, jobParams);
    }

    private JobParameters createJobParameters() {
        String nextId = VsbJobParametersIncrementer.generateNextId();
        return new JobParametersBuilder().addString("ID", nextId).toJobParameters();
    }
}
