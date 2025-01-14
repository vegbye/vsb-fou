package vsb.fou.rest.spring.client;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vsb.fou.rest.spring.api.HelloWorldRequest;
import vsb.fou.rest.spring.api.HelloWorldResponse;
import vsb.fou.rest.spring.api.Metadata;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vegard S. Bye
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainCtxResearchClient.class)
@Ignore("funker ikke helt i 2024")
public class HelloWorldClientIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldClientIT.class);
    @Autowired
    private HelloWorldClient helloWorldClient;

    @Test
    public void test_execute() {
        String jepsipepsi = "jepsipepsi";
        String response = helloWorldClient.doExeute(jepsipepsi);
        LOGGER.info("response = " + response);
        assertThat(response, containsString(jepsipepsi));
    }

    @Test
    public void test_sayHello() {
        HelloWorldRequest request = new HelloWorldRequest();
        Metadata metadata = new Metadata();
        metadata.setMessageId("123456");
        metadata.setSenderId(this.getClass().getSimpleName());
        request.metadata = metadata;
        request.msg = "Wilses vei 8A";

        ResponseEntity<HelloWorldResponse> responseEntity = helloWorldClient.sayHello(request);
        HttpStatus statusCode = responseEntity.getStatusCode();
        assertThat(statusCode, is(HttpStatus.OK));
        HelloWorldResponse researchResponse = responseEntity.getBody();
        assertThat(researchResponse.metadata.getMessageId(), is(request.metadata.getMessageId()));
        assertThat(researchResponse.result, containsString(request.msg));
    }
}
