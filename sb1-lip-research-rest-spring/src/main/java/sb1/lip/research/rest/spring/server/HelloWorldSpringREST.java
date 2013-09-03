package sb1.lip.research.rest.spring.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sb1.lip.research.rest.spring.api.HelloWorldRequest;
import sb1.lip.research.rest.spring.api.HelloWorldResponse;
import sb1.lip.research.rest.spring.api.Metadata;

import java.util.Date;

/**
 * @author Vegard S. Bye
 */
@Controller
@RequestMapping("/research")
public class HelloWorldSpringREST {

    private static final Logger REQUEST_LOGGER = LoggerFactory.getLogger("REQUEST." + HelloWorldSpringREST.class.getSimpleName());
    private static final Logger RESPONSE_LOGGER = LoggerFactory.getLogger("RESPONSE." + HelloWorldSpringREST.class.getSimpleName());

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public HelloWorldResponse execute(@PathVariable("id") String id) {
        REQUEST_LOGGER.info("execute(" + id + ")");
        String result = doService(id);
        RESPONSE_LOGGER.info("execute(" + id + ")->" + result);
        HelloWorldResponse response = new HelloWorldResponse();
        response.result = result;
        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public HelloWorldResponse sayHello(@RequestBody HelloWorldRequest request) {
        REQUEST_LOGGER.info("sayHello: MessageId:" + request.metadata.getMessageId() + " SenderId:" + request.metadata.getSenderId() + " Msg:" + request.msg);
        String result = doService(request.msg);
        HelloWorldResponse response = new HelloWorldResponse();
        Metadata metadata = new Metadata();
        metadata.setSenderId(this.getClass().getSimpleName());
        metadata.setMessageId(request.metadata.getMessageId());
        response.metadata = metadata;
        response.result = result;
        REQUEST_LOGGER.info("sayHello: MessageId:" + response.metadata.getMessageId() + " SenderId:" + response.metadata.getSenderId() + " Result:" + response.result);
        return response;
    }

    private String doService(String id) {
        return "Hei: '" + id + "' " + new Date();
    }
}

