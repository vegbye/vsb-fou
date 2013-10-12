package vsb.fou.db.h2;

import org.h2.tools.DeleteDbFiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class HelloWorldH2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldH2.class);
    @Resource
    private DataSource dataSource;

    /**
     * Called when ran from command line.
     *
     * @param args ignored
     */
    public static void main(String... args) throws Exception {
        new HelloWorldH2().doIt();

    }

    public void doIt() throws ClassNotFoundException, SQLException {
        // delete the database named 'test' in the user home directory
        DeleteDbFiles.execute("~", "test", true);

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:HelloWorldH2");
        Statement stat = conn.createStatement();

        // this line would initialize the database
        // from the SQL script file 'init.sql'
        // stat.execute("runscript from 'init.sql'");

        boolean execute = stat.execute("create table test(id int primary key, name varchar(255))");
        System.out.println("execute = " + execute);
        boolean execute1 = stat.execute("insert into test values(1, 'Hello Vegard')");
        System.out.println("execute1 = " + execute1);
        ResultSet rs;
        rs = stat.executeQuery("select * from test");
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
        stat.close();
        conn.close();
    }

    public void doItSpring() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("create table employee (id int, name varchar)");
        jdbcTemplate.execute("insert into employee (id, name) values (1, 'Vegard')");

        List<String> name = jdbcTemplate.query("select * from employee", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("name");
            }
        });
        LOGGER.info("name = " + name);
    }


}
