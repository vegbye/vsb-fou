package vsb.fou.db.h2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class EmployeeServiceH2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceH2.class);
    @Autowired
    private DataSource dataSource;

    public void insert(String primaryKey, String name) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        jdbcTemplate.execute("create table employee (id int, name varchar (255))");
        jdbcTemplate.execute("insert into employee (id, name) values (" + primaryKey + ", '" + name + "')");

        List<String> nameList = jdbcTemplate.query("select * from employee", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("name");
            }
        });
        LOGGER.info("nameList = " + nameList);
    }


}
