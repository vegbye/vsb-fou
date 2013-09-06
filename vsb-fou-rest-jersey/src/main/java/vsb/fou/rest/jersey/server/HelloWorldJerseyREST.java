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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

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
    @Path("hente")
    @Produces(MediaType.APPLICATION_JSON)
    public HelloWorldResponse getHello() {
        REQUEST_LOGGER.info("GET hello!");

        HelloWorldResponse response = new HelloWorldResponse();
        response.metadata = new Metadata();
        response.metadata.setSenderId(this.getClass().getSimpleName());
        response.metadata.setMessageId(Long.toString(System.currentTimeMillis()));
        try {
            response.resultDataList = new ArrayList<ResultData>();
            response.resultDataList.add(getResultData("GET.1"));
            response.resultDataList.add(getResultData("GET.2"));
            response.resultDataList.add(getResultData("GET.3"));
            response.resultDataList.add(getResultData("GET.4"));
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setName(e.toString());
            resultData.setStatus("FAILED");
            response.resultDataList = new ArrayList<ResultData>();
            response.resultDataList.add(resultData);
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
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HelloWorldResponse getHelloId(@PathParam("id") String id) {
        REQUEST_LOGGER.info("GET hello! id:'" + id + "'");

        HelloWorldResponse response = new HelloWorldResponse();
        response.metadata = new Metadata();
        response.metadata.setSenderId(this.getClass().getSimpleName());
        response.metadata.setMessageId(Long.toString(System.currentTimeMillis()));
        try {
            response.resultDataList = new ArrayList<ResultData>();
            response.resultDataList.add(getResultData("GET.1:" + id));
            response.resultDataList.add(getResultData("GET.2:" + id));
            response.resultDataList.add(getResultData("GET.3:" + id));
            response.resultDataList.add(getResultData("GET.4:" + id));
            response.resultDataList.add(getResultData("GET.5:" + id));
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setName(e.toString());
            resultData.setStatus("FAILED");
            response.resultDataList = new ArrayList<ResultData>();
            response.resultDataList.add(resultData);
        }
        RESPONSE_LOGGER.info("GET:" + response);
        return response;
    }

    @POST
    @Path("poste")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HelloWorldResponse postHello(HelloWorldRequest request) {
        REQUEST_LOGGER.info("POST hello:" + request);
        HelloWorldResponse response = new HelloWorldResponse();
        response.metadata = new Metadata();
        response.metadata.setSenderId(this.getClass().getSimpleName());
        response.metadata.setMessageId(request.metadata.getMessageId());
        try {
            ResultData resultData = new ResultData();
            String hello = helloWorldService.sayHello("POST");
            resultData.setName(hello);
            resultData.setStatus("OK");
            response.resultDataList = new ArrayList<ResultData>();
            response.resultDataList.add(resultData);
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setName(e.toString());
            resultData.setStatus("FAILED");
            response.resultDataList = new ArrayList<ResultData>();
            response.resultDataList.add(resultData);
        }
        RESPONSE_LOGGER.info("POST:" + response);
        return response;
    }

}
