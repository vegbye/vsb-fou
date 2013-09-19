package vsb.fou.rest.jersey.server;

import org.springframework.beans.factory.annotation.Autowired;
import vsb.fou.rest.jersey.api.HelloWorldRequest;
import vsb.fou.rest.jersey.api.HelloWorldResponse;
import vsb.fou.rest.jersey.api.Metadata;
import vsb.fou.rest.jersey.api.ResultData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vegard S. Bye
 */
@Path("/helloworld")
public class HelloWorldJerseyREST {

    private static int counter = 0;
    /**
     * NB! MÅ være @Autowired ikke @Resource !?
     */
    @Autowired
    private HelloWorldService helloWorldService;

    public HelloWorldJerseyREST() {
        counter++;
        System.out.println("----------> counter = " + counter);
    }

    @GET
    @Path("/hente")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getHello() {
        HelloWorldResponse entity = new HelloWorldResponse();
        Metadata metadata = new Metadata();
        metadata.setSenderId(this.getClass().getSimpleName());
        metadata.setMessageId(Long.toString(System.currentTimeMillis()));
        entity.setMetadata(metadata);
        List<ResultData> resultDataList = new ArrayList<ResultData>();
        resultDataList.add(getResultData("GET.1"));
        resultDataList.add(getResultData("GET.2"));
        resultDataList.add(getResultData("GET.3"));
        resultDataList.add(getResultData("GET.4"));
        entity.setResultDataList(resultDataList);
        return VsbRestUtils.okResponse(entity);
    }

    @GET
    @Path("/params")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getHelloWithParams(@DefaultValue("123456") @QueryParam("messageId") String messageId) {
        HelloWorldResponse entity = new HelloWorldResponse();
        Metadata metadata = new Metadata();
        metadata.setSenderId(this.getClass().getSimpleName());
        metadata.setMessageId(Long.toString(System.currentTimeMillis()));
        entity.setMetadata(metadata);
        List<ResultData> resultDataList = new ArrayList<ResultData>();
        resultDataList.add(getResultData("GET.1"));
        resultDataList.add(getResultData("GET.2"));
        resultDataList.add(getResultData("GET.3"));
        resultDataList.add(getResultData("GET.4"));
        entity.setResultDataList(resultDataList);
        return VsbRestUtils.okResponse(entity);
    }

    @GET
    @Path("/henteid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHelloId(@PathParam("id") String id) {
        HelloWorldResponse entity = new HelloWorldResponse();
        Metadata metadata = new Metadata();
        metadata.setSenderId(this.getClass().getSimpleName());
        metadata.setMessageId(Long.toString(System.currentTimeMillis()));
        entity.setMetadata(metadata);
        if ("kast".equalsIgnoreCase(id)) {
            throw new VsbServerException("Jeg kaster en feil!");
        }
        List<ResultData> resultDataList = new ArrayList<ResultData>();
        resultDataList.add(getResultData("GET.1:" + id));
        resultDataList.add(getResultData("GET.2:" + id));
        resultDataList.add(getResultData("GET.3:" + id));
        resultDataList.add(getResultData("GET.4:" + id));
        resultDataList.add(getResultData("GET.5:" + id));
        entity.setResultDataList(resultDataList);
        return VsbRestUtils.okResponse(entity);
    }

    @POST
    @Path("/poste")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response postHello(HelloWorldRequest request) {
        HelloWorldResponse entity = new HelloWorldResponse();
        Metadata metadata = new Metadata();
        metadata.setSenderId(this.getClass().getSimpleName());
        metadata.setMessageId(request.getMetadata().getMessageId());
        entity.setMetadata(metadata);
        ResultData resultData = new ResultData();
        String hello = helloWorldService.sayHello("POST");
        resultData.setName(hello);
        resultData.setStatus("OK");
        List<ResultData> resultDataList = new ArrayList<ResultData>();
        resultDataList.add(resultData);
        entity.setResultDataList(resultDataList);
        return VsbRestUtils.okResponse(entity);
    }

    private ResultData getResultData(String hello) {
        ResultData resultData = new ResultData();
        resultData.setName(helloWorldService.sayHello(hello));
        resultData.setStatus("OK");
        return resultData;
    }
}
