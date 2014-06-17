package vsb.fou.batch.spring.poc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Vegard S. Bye
 */
@Transactional
@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StepScopePocCtx.class})
public class StepScopePocCtxTest {

    @Resource
    private ApplicationContext ctx;

    @Test
    public void testAppCtx() {
        Object stepScopedReader = ctx.getBean("stepScopedReader");
        assertThat(stepScopedReader, notNullValue());
        assertThat(ctx.getBean("minJobb"), notNullValue());
        assertThat(ctx.getBean("mittAspekt"), notNullValue());
    }
}
