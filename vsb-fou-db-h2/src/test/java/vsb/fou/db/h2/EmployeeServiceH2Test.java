package vsb.fou.db.h2;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestCtxDatabaseH2.class})
public class EmployeeServiceH2Test {

    @Resource
    private EmployeeServiceH2 employeeServiceH2;

    @Test
    public void testIt() {
        employeeServiceH2.doIt();
    }
}
