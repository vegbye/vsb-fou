package vsb.fou.rest.jersey.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import vsb.fou.rest.jersey.api.HelloWorldRequest;
import vsb.fou.rest.jersey.api.HelloWorldResponse;
import vsb.fou.rest.jersey.api.Metadata;
import vsb.fou.rest.jersey.api.ResultData;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vegard S. Bye
 */
@Path("/helloworld")
public class HelloWorldJerseyREST {

    private static final Logger ERROR_LOGGER = LoggerFactory.getLogger("ERROR." + HelloWorldJerseyREST.class.getSimpleName());
    private static final Logger REQUEST_LOGGER = LoggerFactory.getLogger("REQUEST." + HelloWorldJerseyREST.class.getSimpleName());
    private static final Logger RESPONSE_LOGGER = LoggerFactory.getLogger("RESPONSE." + HelloWorldJerseyREST.class.getSimpleName());
    /**
     * NB! MÅ være @Autowired ikke @Resource !?
     */
    @Autowired
    private HelloWorldService helloWorldService;

    @GET
    @Path("/hente")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public HelloWorldResponse getHello() {
        REQUEST_LOGGER.info("GET hello!");

        HelloWorldResponse response = new HelloWorldResponse();
        Metadata metadata = new Metadata();
        metadata.setSenderId(this.getClass().getSimpleName());
        metadata.setMessageId(Long.toString(System.currentTimeMillis()));
        response.setMetadata(metadata);
        try {
            List<ResultData> resultDataList = new ArrayList<ResultData>();
            resultDataList.add(getResultData("GET.1"));
            resultDataList.add(getResultData("GET.2"));
            resultDataList.add(getResultData("GET.3"));
            resultDataList.add(getResultData("GET.4"));
            response.setResultDataList(resultDataList);
        } catch (Exception e) {
            ERROR_LOGGER.error("/helloworld/hente", e);
            throw new InternalServerErrorException(e.toString(), e);
        }
        RESPONSE_LOGGER.info("GET:" + response);
        return response;
    }

    private ResultData getResultData(String hello) {
        ResultData resultData = new ResultData();
        resultData.setName(helloWorldService.sayHello(hello));
        resultData.setStatus("OK");
        return resultData;
    }

    @GET
    @Path("/henteid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HelloWorldResponse getHelloId(@PathParam("id") String id) {
        REQUEST_LOGGER.info("GET hello! id:'" + id + "'");

        HelloWorldResponse response = new HelloWorldResponse();
        Metadata metadata = new Metadata();
        metadata.setSenderId(this.getClass().getSimpleName());
        metadata.setMessageId(Long.toString(System.currentTimeMillis()));
        response.setMetadata(metadata);
        try {
            if ("kast".equalsIgnoreCase(id)) {
                throw new RuntimeException("Jeg kaster en feil!");
            }
            List<ResultData> resultDataList = new ArrayList<ResultData>();
            resultDataList.add(getResultData("GET.1:" + id));
            resultDataList.add(getResultData("GET.2:" + id));
            resultDataList.add(getResultData("GET.3:" + id));
            resultDataList.add(getResultData("GET.4:" + id));
            resultDataList.add(getResultData("GET.5:" + id));
            response.setResultDataList(resultDataList);
        } catch (Exception e) {
            ERROR_LOGGER.error("/helloworld/henteid", e);
            throw new InternalServerErrorException(e.toString(), e);
        }
        RESPONSE_LOGGER.info("GET:" + response);
        return response;
    }

    @POST
    @Path("/poste")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public HelloWorldResponse postHello(HelloWorldRequest request) {
        REQUEST_LOGGER.info("POST hello:" + request.getMetadata() + request.getMsg());
        HelloWorldResponse response = new HelloWorldResponse();
        Metadata metadata = new Metadata();
        metadata.setSenderId(this.getClass().getSimpleName());
        metadata.setMessageId(request.getMetadata().getMessageId());
        response.setMetadata(metadata);
        try {
            ResultData resultData = new ResultData();
            String hello = helloWorldService.sayHello("POST");
            resultData.setName(hello);
            resultData.setStatus("OK");
            List<ResultData> resultDataList = new ArrayList<ResultData>();
            resultDataList.add(resultData);
            response.setResultDataList(resultDataList);
        } catch (Exception e) {
            ERROR_LOGGER.error("/helloworld/hente", e);
            throw new InternalServerErrorException(e.toString(), e);
        }
        RESPONSE_LOGGER.info("POST:" + response);
        return response;
    }

}
