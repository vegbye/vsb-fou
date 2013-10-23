package vsb.fou.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RunMeTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunMeTask.class);

    public void printMe() {
        LOGGER.info("Spring 3.2.4 + Quartz 2.2.1 ~");
    }
}
