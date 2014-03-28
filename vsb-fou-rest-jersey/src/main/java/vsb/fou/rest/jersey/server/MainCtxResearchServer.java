package vsb.fou.rest.jersey.server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan("vsb.fou.rest.jersey.server")
@EnableAspectJAutoProxy
public class MainCtxResearchServer {

    @PostConstruct
    public void bridgeJulToSlf4j() {
        org.slf4j.bridge.SLF4JBridgeHandler.removeHandlersForRootLogger();
        org.slf4j.bridge.SLF4JBridgeHandler.install();
    }

    @PostConstruct
    public void addTruncatedElements() {
        VsbRestAspect.addTruncatedRequestParam(HelloWorldJerseyREST.class.getSimpleName() + ".getHelloId", "id");
    }
}
