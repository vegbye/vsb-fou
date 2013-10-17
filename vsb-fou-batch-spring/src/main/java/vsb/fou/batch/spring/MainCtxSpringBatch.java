package vsb.fou.batch.spring;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import vsb.fou.common.InfraConfig;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan(basePackages = "vsb.fou.batch.spring", excludeFilters = {@ComponentScan.Filter(InfraConfig.class)})
public class MainCtxSpringBatch {

    @Resource
    private DataSource dataSource;
    @Resource
    private PlatformTransactionManager transactionManager;
    @Resource
    private JobRepository jobRepository;

    @Bean
    public MapJobRepositoryFactoryBean jobRepository() {
        return new MapJobRepositoryFactoryBean(transactionManager);
    }

    @Bean
    public JobLauncher jobLauncher() {
        SimpleJobLauncher bean = new SimpleJobLauncher();
        bean.setJobRepository(jobRepository);
        return bean;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public ItemReader productReader() {
        FlatFileItemReader<Product> bean = new FlatFileItemReader<>();
        bean.setLinesToSkip(1);
        bean.setResource(new ClassPathResource("/products.csv"));
        DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"PRODUCT_ID", "NAME", "DESCRIPTION", "PRICE"});
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new ProductFieldSetMapper());
        bean.setLineMapper(lineMapper);
        return bean;
    }

    @Bean
    public ItemWriter productWriter() {
        return new ProductJdbcItemWriter(dataSource);
    }


}
