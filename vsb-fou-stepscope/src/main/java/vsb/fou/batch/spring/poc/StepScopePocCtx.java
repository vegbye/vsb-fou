package vsb.fou.batch.spring.poc;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@ComponentScan(value = "vsb.fou")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableBatchProcessing
@EnableScheduling
public class StepScopePocCtx {

    @Bean(destroyMethod = "shutdown")
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2)
                .setName("vsbStepScopeTestDb")
                .addScript("/org/springframework/batch/core/schema-h2.sql")
                .build();
    }

    @PostConstruct
    public void bridgeJulToSlf4j() {
        org.slf4j.bridge.SLF4JBridgeHandler.removeHandlersForRootLogger();
        org.slf4j.bridge.SLF4JBridgeHandler.install();
    }
}
