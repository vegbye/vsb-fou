package sb1.lip.research.rest.jersey.server;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * @author Vegard S. Bye
 */
public class HelloWorldApplication extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public HelloWorldApplication() {
        register(RequestContextFilter.class);
        register(HelloWorldJerseyREST.class);
        register(HelloWorldService.class);
    }
}
