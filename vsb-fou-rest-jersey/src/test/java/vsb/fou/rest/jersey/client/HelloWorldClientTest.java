package vsb.fou.rest.jersey.client;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sb1.lip.research.common.DemandsRunningJettyServerTestCategory;
import vsb.fou.rest.jersey.api.HelloWorldRequest;
import vsb.fou.rest.jersey.api.HelloWorldResponse;
import vsb.fou.rest.jersey.api.Metadata;
import vsb.fou.rest.jersey.server.HelloWorldJerseyREST;

import javax.annotation.Resource;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Vegard S. Bye
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainCtxResearchClient.class)
@Category(DemandsRunningJettyServerTestCategory.class)
public class HelloWorldClientTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldClientTest.class);
    @Resource
    private HelloWorldClient helloWorldClient;

    @Test
    public void test_getHello() {
        HelloWorldResponse response = helloWorldClient.getHelloWorld();
        LOGGER.info("metadata.senderId:" + response.metadata.getSenderId());
        LOGGER.info("metadata.messageId:" + response.metadata.getMessageId());
        LOGGER.info("response.result = " + response.result);
        assertThat(response.metadata, notNullValue());
        assertThat(response.metadata.getMessageId(), notNullValue());
        assertThat(response.metadata.getSenderId(), is(HelloWorldJerseyREST.class.getSimpleName()));
        assertThat(response.result, containsString("Hello 'GET'"));
    }

    @Test
    public void test_postHello() {
        HelloWorldRequest request = new HelloWorldRequest();
        request.metadata = new Metadata();
        request.metadata.setSenderId(this.getClass().getSimpleName());
        request.metadata.setMessageId(Long.toString(System.currentTimeMillis()));
        request.msg = "Hei paa deg:" + new Date();
        HelloWorldResponse response = helloWorldClient.postHelloWorld(request);
        LOGGER.info("metadata.senderId:" + response.metadata.getSenderId());
        LOGGER.info("metadata.messageId:" + response.metadata.getMessageId());
        LOGGER.info("response.result = " + response.result);

        assertThat(response.metadata, notNullValue());
        assertThat(response.metadata.getMessageId(), notNullValue());
        assertThat(response.metadata.getMessageId(), is(request.metadata.getMessageId()));
        assertThat(response.metadata.getSenderId(), is(HelloWorldJerseyREST.class.getSimpleName()));
        assertThat(response.result, containsString("Hello 'POST'"));
    }
}
