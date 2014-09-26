package vsb.fou.db.h2;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestCtxDatabaseH2.class})
public class EmployeeServiceH2Test {

    @Autowired
    private EmployeeServiceH2 employeeServiceH2;

    @Test
    public void testIt1() {
        employeeServiceH2.insert("2", "Vegard Bye 2");
        employeeServiceH2.insert("3", "Vegard Bye 3");
    }

    @Test
    public void testIt2() {
        employeeServiceH2.insert("4", "Vegard Bye 4");
        employeeServiceH2.insert("5", "Vegard Bye 5");
    }
}
