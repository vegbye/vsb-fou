package vsb.fou.db.h2;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestCtxDatabaseH2.class})
public class HelloWorldH2Test {

    @Resource
    private HelloWorldH2 helloWorldH2;

    @Test
    public void testIt() {
        helloWorldH2.doItSpring();
    }
}
