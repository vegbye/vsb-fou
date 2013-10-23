package vsb.fou.batch.spring.infra;

import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.support.incrementer.AbstractSequenceMaxValueIncrementer;
import org.springframework.transaction.PlatformTransactionManager;
import vsb.fou.common.InfraConfig;

import javax.sql.DataSource;

import static org.mockito.Mockito.mock;

/**
 * @author Vegard S. Bye
 */
@Configuration
@InfraConfig
public class MockCtxSpringBatch {

    @Bean
    public JobExplorer jobExplorer() {
        return mock(JobExplorer.class);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return mock(TaskExecutor.class);
    }

    @Bean
    public DataSource dataSource() {
        return mock(DataSource.class);
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return mock(PlatformTransactionManager.class);
    }

    @Bean
    public JobRepository jobRepository() {
        return mock(JobRepository.class);
    }

    @Bean
    public AbstractSequenceMaxValueIncrementer productSequence() {
        return mock(AbstractSequenceMaxValueIncrementer.class);
    }
}
