package vsb.fou.batch.spring.poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ImportPersonJobCtx {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportPersonJobCtx.class);

    @Bean
    public ItemReader<Person> personReader() {
        final List<Person> persons = new ArrayList<>();
        persons.add(new Person("Vegard", "Bye"));
        persons.add(new Person("Sally", "Saadi"));

        return new ItemReader<Person>() {
            private int index = 0;

            @Override
            public Person read() {
                Person person = persons.get(index);
                index++;
                LOGGER.info("Leste: " + person);
                return person;
            }
        };
    }

    @Bean
    public ItemProcessor<Person, Person> personProcessor() {
        return new PersonItemProcessor();
    }

    @Bean
    public ItemWriter<Person> personWriter() {
        return new ItemWriter<Person>() {

            @Override
            public void write(List<? extends Person> items) throws Exception {
                for (Person p : items) {
                    LOGGER.info("Skriver: " + p);
                }
            }
        };
    }

    @Bean
    public Job importPersonsJob(JobBuilderFactory jobs, Step importPersonsStep) {
        return jobs.get("importPersonsJob")
                .incrementer(new RunIdIncrementer())
                .flow(importPersonsStep)
                .end()
                .build();
    }

    @Bean
    public Step importPersonsStep(StepBuilderFactory stepBuilderFactory,
                                  ItemReader<Person> personReader,
                                  ItemWriter<Person> personWriter,
                                  ItemProcessor<Person, Person> personProcessor) {
        return stepBuilderFactory.get("importPersonsStep")
                .<Person, Person>chunk(10)
                .reader(personReader)
                .processor(personProcessor)
                .writer(personWriter)
                .build();
    }

}
