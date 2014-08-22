package vsb.fou.batch.spring.poc.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import vsb.fou.batch.spring.poc.util.TimeStamp;

import java.util.Map;

@Component
@StepScope
public class HelloStepTasklet implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloStepTasklet.class);

    private final String createdDate = TimeStamp.getTstamp();

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();
        LOGGER.info("STEPSCOPE - createdDate: " + createdDate + "   ---   " + jobParameters);
        return RepeatStatus.FINISHED;
    }
}
