package vsb.fou.db.h2;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestCtxDatabaseH2.class})
public class EmployeeServiceH2Test {

    @Resource
    private EmployeeServiceH2 employeeServiceH2;
    @Resource
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUpDatabase() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new ClassPathResource("/create-employee.sql"), false);
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new ClassPathResource("/insert-employee.sql"), false);
    }

    @After
    public void tearDownDatabase() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new ClassPathResource("/delete-employee.sql"), false);
    }

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
