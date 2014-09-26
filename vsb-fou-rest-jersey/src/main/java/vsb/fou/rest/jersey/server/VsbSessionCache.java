package vsb.fou.rest.jersey.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class VsbSessionCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(VsbSessionCache.class);

    public void sayHello() {
        LOGGER.info("VsbSessionCache.sayHello");
    }
}
