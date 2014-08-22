package vsb.fou.batch.spring.poc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Vegard S. Bye
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StepScopePocCtx.class})
public class StepScopePocCtxTest {

    @Resource
    private ApplicationContext ctx;

    @Test
    public void testAppCtx() {
        assertThat(ctx, notNullValue());
        assertThat(ctx.getBean("importPersonsJob"), instanceOf(Job.class));
        assertThat(ctx.getBean("helloStepScopeJob"), instanceOf(Job.class));
    }
}
