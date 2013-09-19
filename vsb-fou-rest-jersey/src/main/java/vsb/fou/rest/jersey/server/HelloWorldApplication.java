package vsb.fou.rest.jersey.server;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import vsb.fou.rest.jersey.common.VsbLoggingFilter;

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
        register(new VsbLoggingFilter());
        register(HelloWorldJerseyREST.class);
        register(VsbDefaultExceptionMapper.class);
    }
}
