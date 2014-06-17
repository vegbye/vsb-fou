package vsb.fou.batch.spring.poc;

import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VsbBatchTestCtx {

    //    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();
    }
}
