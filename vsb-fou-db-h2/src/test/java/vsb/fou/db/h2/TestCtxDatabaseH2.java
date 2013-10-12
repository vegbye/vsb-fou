package vsb.fou.db.h2;

import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan("vsb.fou.db.h2")
public class TestCtxDatabaseH2 {

    @Bean(destroyMethod = "dispose")
    public DataSource dataSource() {
        JdbcDataSource targetDataSource = new JdbcDataSource();
        targetDataSource.setURL("jdbc:h2:mem:vsb-fou-test");
        return JdbcConnectionPool.create(targetDataSource);
    }
}
