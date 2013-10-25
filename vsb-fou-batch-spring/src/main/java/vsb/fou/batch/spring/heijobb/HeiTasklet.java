package vsb.fou.batch.spring.heijobb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class HeiTasklet implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeiTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        LOGGER.info("   --- Hei! ---   ");
        return RepeatStatus.FINISHED;
    }
}
