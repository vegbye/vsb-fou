package vsb.fou.batch.spring.poc;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("vsb.fou")
@EnableBatchProcessing
public class StepScopePocCtx {

    @Bean
    @StepScope
    public ItemReader<String> stepScopedReader() {
        return new FlatFileItemReader<>();
    }

}
