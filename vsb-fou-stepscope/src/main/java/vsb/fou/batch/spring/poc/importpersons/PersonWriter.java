package vsb.fou.batch.spring.poc.importpersons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PersonWriter implements ItemWriter<Person> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonWriter.class);
    private final String createdDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);

    @Override
    public void write(List<? extends Person> items) throws Exception {
        for (Person p : items) {
            LOGGER.info(createdDate + " Skriver: " + p);
        }
    }
}
