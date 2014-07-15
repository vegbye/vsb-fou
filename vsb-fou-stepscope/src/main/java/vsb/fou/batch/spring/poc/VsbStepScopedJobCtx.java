package vsb.fou.batch.spring.poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class VsbStepScopedJobCtx {

    private static final Logger LOGGER = LoggerFactory.getLogger(VsbStepScopedJobCtx.class);

    @Resource
    private JobBuilderFactory jobBuilderFactory;
    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job vsbStepScopedJobb() {
        LOGGER.info("Lager vsbStepScopedJobb");
        return jobBuilderFactory.get("vsbStepScopedJobb")
                .start(tulleStep())
                .build();
    }

    @Bean
    public Step tulleStep() {
        return stepBuilderFactory.get("tulleStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        return null;
                    }
                })
                .build();
    }
}
