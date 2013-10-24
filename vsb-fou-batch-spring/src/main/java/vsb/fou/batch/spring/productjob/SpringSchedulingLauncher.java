package vsb.fou.batch.spring.productjob;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vegard S. Bye
 */
@Component
public class SpringSchedulingLauncher {

    @Autowired
    private Job job;
    @Autowired
    private JobLauncher jobLauncher;

    public void launch() throws Exception {
        JobParameters jobParams = createJobParameters();
        jobLauncher.run(job, jobParams);
    }

    private JobParameters createJobParameters() {
        String id = RandomStringUtils.randomAlphanumeric(10);
        return new JobParametersBuilder().addString("ID", id).toJobParameters();
    }

}
