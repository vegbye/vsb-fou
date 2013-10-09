package vsb.fou.jms.activemq.server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import vsb.fou.common.EnvironmentConfiguration;

@Configuration
@ComponentScan(basePackages = "vsb.fou.jms.activemq.server", excludeFilters = {@ComponentScan.Filter(EnvironmentConfiguration.class)})
public class MainCtxActiveMqServer {
}
