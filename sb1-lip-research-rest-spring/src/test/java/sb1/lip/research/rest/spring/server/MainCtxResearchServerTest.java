package sb1.lip.research.rest.spring.server;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author Vegard S. Bye
 */
public class MainCtxResearchServerTest {

    @Test
    public void testAppCtx() throws Exception {
        ApplicationContext parentCtx = new AnnotationConfigApplicationContext(MainCtxResearchServer.class);

        ApplicationContext cxfCtx = new FileSystemXmlApplicationContext(new String[]{"src/main/webapp/WEB-INF/dispatcher-servlet.xml"}, parentCtx);

//        assertThat(cxfCtx.getBean("cxfResearchWS"), instanceOf(EndpointDefinitionParser.SpringEndpointImpl.class));
//        assertThat(cxfCtx.getBean("researchWS"), instanceOf(ResearchWS.class));
    }
}
