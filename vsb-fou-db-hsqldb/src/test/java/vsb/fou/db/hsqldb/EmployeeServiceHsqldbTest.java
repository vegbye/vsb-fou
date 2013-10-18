package vsb.fou.db.hsqldb;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestCtxDatabaseHsqldb.class})
public class EmployeeServiceHsqldbTest {

    @Autowired
    private EmployeeServiceHsqldb employeeServiceHsqldb;

    @Test
    public void testIt() {
        employeeServiceHsqldb.doIt();
    }
}
