package vsb.fou.rest.jersey.server;

import org.glassfish.jersey.filter.LoggingFilter;

import java.util.logging.Logger;

public class VsbLoggingFilter extends LoggingFilter {
    public VsbLoggingFilter(Logger logger, boolean printEntity) {
        super(logger, printEntity);
    }
}
