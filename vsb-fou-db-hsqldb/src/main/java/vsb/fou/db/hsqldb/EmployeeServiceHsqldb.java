package vsb.fou.db.hsqldb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class EmployeeServiceHsqldb {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceHsqldb.class);
    @Resource
    private DataSource dataSource;

    public void doIt() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("create table employee (id int, name varchar (255))");
        jdbcTemplate.execute("insert into employee (id, name) values (1, 'Vegard S. Bye')");

        List<String> name = jdbcTemplate.query("select * from employee", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("name");
            }
        });
        LOGGER.info("name = " + name);
    }


}
