package vsb.fou.ws.cxf.server;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * @author Vegard S. Bye
 */
public class MainCtxResearchServerTest {

    @Test
    public void testAppCtx() throws Exception {
        ApplicationContext parentCtx = new AnnotationConfigApplicationContext(MainCtxResearchServer.class);

        FileSystemXmlApplicationContext cxfCtx = new FileSystemXmlApplicationContext(new String[]{"src/main/webapp/WEB-INF/cxf-servlet.xml"}, parentCtx);

        assertThat(cxfCtx.getBean("endpointHelloWorldCxfWS"), instanceOf(javax.xml.ws.Endpoint.class));
        assertThat(cxfCtx.getBean("helloWorldCxfWS"), instanceOf(HelloWorldCxfWS.class));
    }

}
