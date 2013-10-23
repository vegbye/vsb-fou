package vsb.fou.batch.spring.web;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import vsb.fou.common.InfraConfig;

import javax.sql.DataSource;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan(basePackages = "vsb.fou.batch.spring.web", excludeFilters = {@ComponentScan.Filter(InfraConfig.class)})
public class MainCtxSpringBatch {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private JobExplorer jobExplorer;

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer bean = new PropertyPlaceholderConfigurer();
        bean.setLocation(new ClassPathResource("/vsb-fou-batch-spring.properties"));
        return bean;
    }

    @Bean
    public JobOperator jobOperator() {
        SimpleJobOperator bean = new SimpleJobOperator();
        bean.setJobLauncher(jobLauncher());
        bean.setJobRegistry(jobRegistry());
        bean.setJobExplorer(jobExplorer);
        bean.setJobRepository(jobRepository);
        return bean;
    }

    @Bean
    public JobRegistry jobRegistry() {
        return new MapJobRegistry();
    }

    @Bean
    public JobLauncher jobLauncher() {
        SimpleJobLauncher bean = new SimpleJobLauncher();
        bean.setJobRepository(jobRepository);
        bean.setTaskExecutor(taskExecutor);
        return bean;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

}
