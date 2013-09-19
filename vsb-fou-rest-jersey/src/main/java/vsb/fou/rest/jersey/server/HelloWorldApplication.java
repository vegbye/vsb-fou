package vsb.fou.rest.jersey.server;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import java.util.logging.Logger;

/**
 * @author Vegard S. Bye
 */
public class HelloWorldApplication extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public HelloWorldApplication() {
        register(RequestContextFilter.class);
        register(JacksonFeature.class);
        register(new LoggingFilter(Logger.getLogger("TRANSACTION.REST"), true));
        register(HelloWorldJerseyREST.class);
        register(VsbDefaultExceptionMapper.class);
        register(VsbServerExceptionMapper.class);
    }
}
