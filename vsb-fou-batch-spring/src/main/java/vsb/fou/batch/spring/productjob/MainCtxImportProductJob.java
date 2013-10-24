package vsb.fou.batch.spring.productjob;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.FileSystemResource;
import vsb.fou.batch.spring.productjob.domain.Product;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan("vsb.fou.batch.spring.productjob")
@ImportResource("classpath:/import-products-job.xml")
public class MainCtxImportProductJob {

    @Value("${vsb-fou-batch-spring.target.file}")
    private String targetFile;

    @Bean
    public ItemReader productReader() {
        FlatFileItemReader<Product> bean = new FlatFileItemReader<>();
        bean.setLinesToSkip(1);
        bean.setResource(new FileSystemResource(targetFile));
        DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"NAME", "DESCRIPTION", "PRICE"});
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new ProductFieldSetMapper());
        bean.setLineMapper(lineMapper);
        return bean;
    }

}
