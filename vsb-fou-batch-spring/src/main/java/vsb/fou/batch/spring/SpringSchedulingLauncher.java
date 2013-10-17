package vsb.fou.batch.spring;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Vegard S. Bye
 */
@Component
public class SpringSchedulingLauncher {

    @Resource
    private Job job;
    @Resource
    private JobLauncher jobLauncher;

    public void launch() throws Exception {
        JobParameters jobParams = createJobParameters();
        jobLauncher.run(job, jobParams);
    }

    private JobParameters createJobParameters() {
        return new JobParametersBuilder().addString("ID", UUID.randomUUID().toString()).toJobParameters();
    }

}
