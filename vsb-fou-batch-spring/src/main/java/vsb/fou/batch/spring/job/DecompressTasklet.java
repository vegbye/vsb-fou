package vsb.fou.batch.spring.job;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipInputStream;

/**
 * @author Vegard S. Bye
 */
@Component
public class DecompressTasklet implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DecompressTasklet.class);
    @Value("${vsb-fou-batch-spring.input.file}")
    private String inputFile;
    @Value("${vsb-fou-batch-spring.target.file}")
    private String targetFile;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String randomTall = RandomStringUtils.randomAlphanumeric(10);
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().putString("randomTall", randomTall);
        LOGGER.info("randomTall:" + randomTall);
        Long jobExecutionId = chunkContext.getStepContext().getStepExecution().getJobExecutionId();
        Long stepId = chunkContext.getStepContext().getStepExecution().getId();
        Long jobInstanceId = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getId();
        Object id = chunkContext.getStepContext().getJobParameters().get("ID");
        LOGGER.info("ID:" + id + " jobExecutionId:" + jobExecutionId + " stepId:" + stepId + " jobInstanceId:" + jobInstanceId);
        LOGGER.info("inputFile:" + inputFile);
        ClassPathResource inputResource = new ClassPathResource(inputFile);
        ZipInputStream zis = new ZipInputStream(
                new BufferedInputStream(inputResource.getInputStream()));
        File target = new File(targetFile);
        LOGGER.info("target:" + target.getAbsolutePath());
        target.getParentFile().mkdirs();
        BufferedOutputStream dest;
        while (zis.getNextEntry() != null) {
            if (!target.exists()) {
                target.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(target
            );
            dest = new BufferedOutputStream(fos);
            IOUtils.copy(zis, dest);
            dest.flush();
            dest.close();
        }
        zis.close();
        if (!target.exists()) {
            throw new IllegalStateException("Could not decompress anything from the archive!");
        }
        LOGGER.info("input er unzippet!");
        return RepeatStatus.FINISHED;
    }
}
