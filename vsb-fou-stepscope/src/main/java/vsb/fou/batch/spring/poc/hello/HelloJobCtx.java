package vsb.fou.batch.spring.poc.hello;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloJobCtx {

    @Bean
    public Job helloStepScopeJob(JobBuilderFactory jobs, Step helloStep) {
        return jobs.get("helloStepScopeJob")
                .incrementer(new RunIdIncrementer())
                .flow(helloStep)
                .end()
                .build();
    }

    @Bean
    public Step helloStep(StepBuilderFactory stepBuilderFactory,
                          Tasklet helloTasklet) {
        return stepBuilderFactory.get("helloStep")
                .tasklet(helloTasklet)
                .build();
    }

}
