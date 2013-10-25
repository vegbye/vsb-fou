package vsb.fou.batch.spring.common;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Vegard S. Bye
 */
@Component
public class VsbJobParametersIncrementer implements JobParametersIncrementer {

    public static final String ID = "ID";

    public static String generateNextId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public JobParameters getNext(JobParameters parameters) {
        String id = generateNextId();
        return new JobParametersBuilder(parameters)
                .addString(ID, id)
                .toJobParameters();
    }
}
