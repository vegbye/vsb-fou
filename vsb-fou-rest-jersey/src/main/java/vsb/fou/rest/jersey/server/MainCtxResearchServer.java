package vsb.fou.rest.jersey.server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import vsb.fou.common.JulToSlf4jConfig;

import javax.annotation.PostConstruct;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ComponentScan("vsb.fou.rest.jersey.server")
@EnableAspectJAutoProxy
public class MainCtxResearchServer {

    static {
        JulToSlf4jConfig.bridgeJulToSlf4j();
    }

    @PostConstruct
    public void addTruncatedElements() {
        VsbRestAspect.addTruncatedRequestParam(HelloWorldJerseyREST.class.getSimpleName() + ".getHelloId", "id");
    }
}
