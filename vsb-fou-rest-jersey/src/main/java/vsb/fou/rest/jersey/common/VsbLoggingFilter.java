package vsb.fou.rest.jersey.common;

import org.glassfish.jersey.filter.LoggingFilter;

import java.util.logging.Logger;

public class VsbLoggingFilter extends LoggingFilter {

    public VsbLoggingFilter() {
        super(Logger.getLogger("HTTP.REST"), 100 * 1024);
    }
}
