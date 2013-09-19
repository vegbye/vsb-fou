package vsb.fou.rest.jersey.client;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vsb.fou.common.DemandsRunningJettyServerTestCategory;
import vsb.fou.rest.jersey.api.HelloWorldRequest;
import vsb.fou.rest.jersey.api.HelloWorldResponse;
import vsb.fou.rest.jersey.api.Metadata;
import vsb.fou.rest.jersey.server.HelloWorldJerseyREST;

import javax.annotation.Resource;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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
        LOGGER.info("metadata.senderId:" + response.getMetadata().getSenderId());
        LOGGER.info("metadata.messageId:" + response.getMetadata().getMessageId());
        LOGGER.info("response.resultDataList = " + response.getResultDataList());
        assertThat(response.getMetadata(), notNullValue());
        assertThat(response.getMetadata().getMessageId(), notNullValue());
        assertThat(response.getMetadata().getSenderId(), is(HelloWorldJerseyREST.class.getSimpleName()));
        assertThat(response.getResultDataList().get(0).getName(), containsString("Hello 'GET.1'"));
    }

    @Test
    public void test_getHelloParams() {
        HelloWorldResponse response = helloWorldClient.getHelloWorldParams();
        LOGGER.info("metadata.senderId:" + response.getMetadata().getSenderId());
        LOGGER.info("metadata.messageId:" + response.getMetadata().getMessageId());
        LOGGER.info("response.resultDataList = " + response.getResultDataList());
        assertThat(response.getMetadata(), notNullValue());
        assertThat(response.getMetadata().getMessageId(), notNullValue());
        assertThat(response.getMetadata().getSenderId(), is(HelloWorldJerseyREST.class.getSimpleName()));
        assertThat(response.getResultDataList().get(0).getName(), containsString("Hello 'GET.1'"));
    }

    @Test
    public void test_getHelloId() {
        String id = "ID-" + System.currentTimeMillis();
        HelloWorldResponse response = helloWorldClient.getHelloWorldId(id);
        LOGGER.info("metadata.senderId:" + response.getMetadata().getSenderId());
        LOGGER.info("metadata.messageId:" + response.getMetadata().getMessageId());
        LOGGER.info("response.resultDataList = " + response.getResultDataList());
        assertThat(response.getMetadata(), notNullValue());
        assertThat(response.getMetadata().getMessageId(), notNullValue());
        assertThat(response.getMetadata().getSenderId(), is(HelloWorldJerseyREST.class.getSimpleName()));
        assertThat(response.getResultDataList().get(0).getName(), containsString(id));
    }

    @Test
    public void test_getHelloIdKastFeil() {
        try {
            String id = "kast";
            helloWorldClient.getHelloWorldId(id);
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage(), containsString("500"));
            assertThat(e.getMessage(), containsString("Jeg kaster en feil!"));
        }
    }

    @Test
    public void test_postHello_JSON() {
        HelloWorldRequest request = new HelloWorldRequest();
        Metadata metadata = new Metadata();
        metadata.setSenderId(this.getClass().getSimpleName());
        metadata.setMessageId(Long.toString(System.currentTimeMillis()));
        request.setMetadata(metadata);
        request.setMsg("Hei paa deg:" + new Date());
        HelloWorldResponse response = helloWorldClient.postHelloWorldJSON(request);
        LOGGER.info("metadata.senderId:" + response.getMetadata().getSenderId());
        LOGGER.info("metadata.messageId:" + response.getMetadata().getMessageId());
        LOGGER.info("response.resultDataList = " + response.getResultDataList());

        assertThat(response.getMetadata(), notNullValue());
        assertThat(response.getMetadata().getMessageId(), notNullValue());
        assertThat(response.getMetadata().getMessageId(), is(request.getMetadata().getMessageId()));
        assertThat(response.getMetadata().getSenderId(), is(HelloWorldJerseyREST.class.getSimpleName()));
        assertThat(response.getResultDataList().get(0).getName(), containsString("Hello 'POST'"));
        assertThat(response.getResultDataList().size(), is(1));
        assertThat(response.getResultDataList().get(0).getName(), containsString("Hello 'POST'"));
        assertThat(response.getResultDataList().get(0).getStatus(), containsString("OK"));
    }

    @Test
    public void test_postHello_XML() {
        HelloWorldRequest request = new HelloWorldRequest();
        Metadata metadata = new Metadata();
        metadata.setSenderId(this.getClass().getSimpleName());
        metadata.setMessageId(Long.toString(System.currentTimeMillis()));
        request.setMetadata(metadata);
        request.setMsg("Hei paa deg:" + new Date());
        HelloWorldResponse response = helloWorldClient.postHelloWorldXML(request);
        LOGGER.info("metadata.senderId:" + response.getMetadata().getSenderId());
        LOGGER.info("metadata.messageId:" + response.getMetadata().getMessageId());
        LOGGER.info("response.resultDataList = " + response.getResultDataList());

        assertThat(response.getMetadata(), notNullValue());
        assertThat(response.getMetadata().getMessageId(), notNullValue());
        assertThat(response.getMetadata().getMessageId(), is(request.getMetadata().getMessageId()));
        assertThat(response.getMetadata().getSenderId(), is(HelloWorldJerseyREST.class.getSimpleName()));
        assertThat(response.getResultDataList().get(0).getName(), containsString("Hello 'POST'"));
    }
}
