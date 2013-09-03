package sb1.lip.research.ws.cxf.client;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sb1.lip.research.common.DemandsRunningJettyServerTestCategory;
import sb1.lip.research.service._2013._08.PingRequest;
import sb1.lip.research.service._2013._08.PingResponse;
import sb1.lip.research.service._2013._08.ResearchPortType;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * @author Vegard S. Bye
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainCtxResearchClient.class)
@Category(DemandsRunningJettyServerTestCategory.class)
public class ResearchServiceClientTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResearchServiceClientTest.class);
    @Resource
    private ResearchPortType researchClient;

    @Test
    public void test_research_ping() {
        PingRequest request = new PingRequest();
        String requestMsg = "Hei paa deg! " + System.currentTimeMillis();
        request.setMsg(requestMsg);
        PingResponse response = researchClient.ping(request);
        LOGGER.info("response.getSvar = " + response.getSvar());
        assertThat(response.getSvar(), containsString(requestMsg));
    }
}
