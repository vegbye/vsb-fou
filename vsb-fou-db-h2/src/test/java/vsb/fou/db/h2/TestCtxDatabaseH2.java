package vsb.fou.db.h2;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan("vsb.fou.db.h2")
public class TestCtxDatabaseH2 {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName("h2-inmem-db-" + RandomStringUtils.randomNumeric(1000))
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/create-employee.sql")
                .addScript("classpath:/insert-employee.sql")
                .build();
    }
}
