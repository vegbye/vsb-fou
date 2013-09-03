package vsb.kladd;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public final class VsbLog4jConfig {

    private VsbLog4jConfig() {
    }

    private static final Logger log = Logger.getLogger(VsbLog4jConfig.class);

    public static void configure() {
        PropertyConfigurator.configure(createProps());
        log.info("Log4j er konfigurert programmatisk.");
    }

    private static Properties createProps() {
        Properties props = new Properties();
        props.put("log4j.rootLogger", "INFO, stdout");
        props.put("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
        props.put("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
        props.put("log4j.appender.stdout.layout.conversionPattern", "###%d{ISO8601}### [%-5p] [%t] %c{1}.%M.%L - %m%n");
        props.put("log4j.appender.stdout.immediateFlush", "true");

        props.put("log4j.logger.vegard", "DEBUG");
        return props;
    }
}
