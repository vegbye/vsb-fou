package vsb.fou.batch.spring.poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;

import java.util.Date;
import java.util.List;

public class PersonReader implements ItemReader<Person>, StepExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonReader.class);
    private final String createdDate = new Date().toString();
    private final List<Person> persons;
    private int index = 0;

    public PersonReader(List<Person> persons) {
        this.persons = persons;
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
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        JobParameters jobParameters = stepExecution.getJobParameters();
        LOGGER.info("   --- afterStep ---   " + jobParameters);
        return ExitStatus.COMPLETED;
    }
}
