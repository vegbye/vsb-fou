package vsb.fou.db.hsqldb;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan("vsb.fou.db.hsqldb")
public class TestCtxDatabaseHsqldb {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName("hsql-inmem-db-" + RandomStringUtils.randomNumeric(1000))
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
    }
}
