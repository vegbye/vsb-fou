package vsb.fou.batch.spring;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vsb.fou.common.InfraConfig;

import javax.sql.DataSource;

/**
 * @author Vegard S. Bye
 */
@Configuration
@InfraConfig
public class InfraCtxSpringBatch {

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:/var/db/vsb-fou-batch-spring");
        return dataSource;
    }
}
