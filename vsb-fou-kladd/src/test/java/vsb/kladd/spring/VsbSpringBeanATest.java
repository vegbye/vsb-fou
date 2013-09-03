package vsb.kladd.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:vsb-spring-context.xml")
public class VsbSpringBeanATest {

    @Autowired
    private VsbSpringBeanA vsbSpringBeanA;

    @Test
    public void testIt() {
        vsbSpringBeanA.perform();
    }
}
