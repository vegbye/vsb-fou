package vsb.fou.jms.activemq.client;

import org.apache.activemq.broker.BrokerService;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vsb.fou.jms.activemq.server.MainCtxActiveMqServer;
import vsb.fou.jms.activemq.springtestutils.TestCtxActiveMqEnv;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.TextMessage;

import static org.junit.Assert.assertThat;

/**
 * @author Vegard S. Bye
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainCtxActiveMqClient.class, MainCtxActiveMqServer.class, TestCtxActiveMqEnv.class})
public class SyncJmsClientNoStubTest {

    @Resource
    private JmsTemplate jmsTemplate;
    @Resource
    private BrokerService broker;

    @After
    public void stopBroker() throws Exception {
        broker.stop();
    }

    @Test
    public void testIt() throws Exception {
        SyncJmsClient syncJmsClient = new SyncJmsClient();
        syncJmsClient.setJmsTemplate(jmsTemplate);
        String msg = "Hei fra JUnit test:" + System.currentTimeMillis();
        Message message = syncJmsClient.doIt(msg);
        assertThat(message, CoreMatchers.notNullValue());
        assertThat(message, CoreMatchers.instanceOf(TextMessage.class));
        TextMessage textMessage = (TextMessage) message;
        assertThat(textMessage.getText(), CoreMatchers.containsString(msg));
    }
}
