package vsb.fou.batch.spring.poc;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(value = "vsb.fou")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableBatchProcessing
@EnableScheduling
public class StepScopePocCtx {

    @PostConstruct
    public void bridgeJulToSlf4j() {
        org.slf4j.bridge.SLF4JBridgeHandler.removeHandlersForRootLogger();
        org.slf4j.bridge.SLF4JBridgeHandler.install();
    }
}
