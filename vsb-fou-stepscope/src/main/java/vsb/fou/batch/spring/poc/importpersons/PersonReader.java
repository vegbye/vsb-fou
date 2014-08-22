package vsb.fou.batch.spring.poc.importpersons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@StepScope
public class PersonReader implements ItemReader<Person>, StepExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonReader.class);
    private final String createdDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
    private final List<Person> persons;
    private int index = 0;

    public PersonReader() {
        persons = new ArrayList<>();
        persons.add(new Person("Vegard", "Bye"));
        persons.add(new Person("Sally", "Saadi"));
    }

    @Override
    public Person read() {
        if (index >= persons.size()) {
            index = 0;
            return null;
        }
        Person person = persons.get(index);
        index++;
        LOGGER.info(createdDate + " Leste: " + person);
        return person;
    }


    @Override
    public void beforeStep(StepExecution stepExecution) {
        JobParameters jobParameters = stepExecution.getJobParameters();
        LOGGER.info("   --- beforeStep ---   " + jobParameters);
        System.out.println("   --- beforeStep ---   " + jobParameters);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        JobParameters jobParameters = stepExecution.getJobParameters();
        LOGGER.info("   --- afterStep ---   " + jobParameters);
        System.out.println("   --- afterStep ---   " + jobParameters);
        return ExitStatus.COMPLETED;
    }
}
