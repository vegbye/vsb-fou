package vsb.fou.batch.spring.infra;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.incrementer.AbstractSequenceMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.H2SequenceMaxValueIncrementer;
import org.springframework.transaction.PlatformTransactionManager;
import vsb.fou.common.InfraConfig;

import javax.sql.DataSource;

/**
 * @author Vegard S. Bye
 */
@Configuration
@InfraConfig
public class InfraCtxSpringBatch {

    @Autowired
    private JobRegistry jobRegistry;

    /**
     * Denne MÅ vi ha for at Spring Batch Admin skal finne jobbene som er i denne spring-ctx slik at de er launchable.
     */
    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor() {
        JobRegistryBeanPostProcessor bean = new JobRegistryBeanPostProcessor();
        bean.setJobRegistry(jobRegistry);
        return bean;
    }

    /**
     * H2 oppretter en database på disk med de angitte brukernavn/passord om den ikke eksisterer.
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/db/vsb/vsb-fou-batch-spring");
        dataSource.setUsername("vsb");
        dataSource.setPassword("vsb");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JobRepositoryFactoryBean jobRepository() {
        JobRepositoryFactoryBean bean = new JobRepositoryFactoryBean();
        bean.setDataSource(dataSource());
        bean.setTransactionManager(transactionManager());
        return bean;
    }

    @Bean
    public JobExplorerFactoryBean jobExplorer() {
        JobExplorerFactoryBean bean = new JobExplorerFactoryBean();
        bean.setDataSource(dataSource());
        return bean;
    }

    @Bean
    public AbstractSequenceMaxValueIncrementer productSequence() {
        return new H2SequenceMaxValueIncrementer(dataSource(), "VSB.PRODUCT_SEQ");
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor bean = new SimpleAsyncTaskExecutor();
        bean.setConcurrencyLimit(-1);
        return bean;
    }
}
