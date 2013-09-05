package vsb.fou.rest.jersey.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import vsb.fou.rest.jersey.api.HelloWorldRequest;
import vsb.fou.rest.jersey.api.HelloWorldResponse;
import vsb.fou.rest.jersey.api.Metadata;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Vegard S. Bye
 */
@Path("helloworld")
public class HelloWorldJerseyREST {

    private static final Logger REQUEST_LOGGER = LoggerFactory.getLogger("REQUEST." + HelloWorldJerseyREST.class.getSimpleName());
    private static final Logger RESPONSE_LOGGER = LoggerFactory.getLogger("RESPONSE." + HelloWorldJerseyREST.class.getSimpleName());
    /**
     * NB! MÅ være @Autowired ikke @Resource !?
     */
    @Autowired
    private HelloWorldService helloWorldService;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public HelloWorldResponse getHello() {
        REQUEST_LOGGER.info("GET hello!");

        HelloWorldResponse response = new HelloWorldResponse();
        response.metadata = new Metadata();
        response.metadata.setSenderId(this.getClass().getSimpleName());
        response.metadata.setMessageId(Long.toString(System.currentTimeMillis()));
        try {
            response.result = helloWorldService.sayHello("GET");
        } catch (Exception e) {
            e.printStackTrace();
            response.result = e.toString();
        }
        RESPONSE_LOGGER.info("GET:" + response);
        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public HelloWorldResponse postHello(HelloWorldRequest request) {
        REQUEST_LOGGER.info("POST hello:" + request);
        HelloWorldResponse response = new HelloWorldResponse();
        response.metadata = new Metadata();
        response.metadata.setSenderId(this.getClass().getSimpleName());
        response.metadata.setMessageId(request.metadata.getMessageId());
        try {
            response.result = helloWorldService.sayHello("POST");
        } catch (Exception e) {
            e.printStackTrace();
            response.result = e.toString();
        }
        RESPONSE_LOGGER.info("POST:" + response);
        return response;
    }

}
