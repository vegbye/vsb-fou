package vsb.fou.rest.jersey.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vsb.fou.rest.jersey.api.HelloWorldRequest;
import vsb.fou.rest.jersey.api.HelloWorldResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Vegard S. Bye
 */
@Service
public class HelloWorldClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldClient.class);
    @Value("${vsb.fou.rest.baseurl}")
    private String baseUrl;

    public HelloWorldResponse getHelloWorld() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseUrl + "/rest/").path("helloworld");
        Response response = webTarget.request().get();
        LOGGER.info("response.getEntity() ? " + response.getEntity());
        checkResponseForErrors(response);
        return response.readEntity(HelloWorldResponse.class);
    }

    public HelloWorldResponse postHelloWorld(HelloWorldRequest request) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseUrl + "/rest/").path("helloworld");

        Entity<HelloWorldRequest> entity = Entity.entity(request, MediaType.APPLICATION_JSON);
        Response response = webTarget.request(MediaType.APPLICATION_JSON).post(entity, Response.class);

        checkResponseForErrors(response);
        return response.readEntity(HelloWorldResponse.class);
    }

    private void checkResponseForErrors(Response response) {
        LOGGER.info("response.getStatus() = " + response.getStatus());
        LOGGER.info("response.getFamily() = " + response.getStatusInfo().getFamily());
        LOGGER.info("response.getReasonPhrase() = " + response.getStatusInfo().getReasonPhrase());
        LOGGER.info("response.getDate() = " + response.getDate());
        if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
            LOGGER.error("IKKE SUKSESS:" + response.getStatusInfo().getFamily());
            throw new RuntimeException("Ikke suksess: " + response.getStatus());
        }
    }
}
