package vsb.fou.batch.spring.productjob;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Vegard S. Bye
 */
@Component
@Scope("step")
public class CleanUpTasklet implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(CleanUpTasklet.class);
    @Value("${vsb-fou-batch-spring.target.file}")
    private String targetFile;
    @Value("#{jobExecutionContext['randomTall']}")
    private String randomTall;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        LOGGER.info("randomTall:" + randomTall);
        Long jobExecutionId = chunkContext.getStepContext().getStepExecution().getJobExecutionId();
        Long stepId = chunkContext.getStepContext().getStepExecution().getId();
        Long jobInstanceId = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getId();
        Object id = chunkContext.getStepContext().getJobParameters().get("ID");
        LOGGER.info("ID:" + id + " jobExecutionId:" + jobExecutionId + " stepId:" + stepId + " jobInstanceId:" + jobInstanceId);
        LOGGER.info("deleting targetFile:" + targetFile);
        FileUtils.forceDelete(new File(targetFile));
        LOGGER.info("targetFile er slettet!");
        return RepeatStatus.FINISHED;
    }
}
