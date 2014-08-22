package vsb.fou.batch.spring.poc.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class HelloTasklet implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloTasklet.class);

    private final String createdDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();
        LOGGER.info("STEP - createdDate: " + createdDate + "   ---   " + jobParameters);
        return RepeatStatus.FINISHED;
    }
}
