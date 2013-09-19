package vsb.fou.rest.jersey.common;

import org.glassfish.jersey.filter.LoggingFilter;

import java.util.logging.Logger;

public class VsbLoggingFilter extends LoggingFilter {

    public VsbLoggingFilter() {
        super(Logger.getLogger("TRANSACTION.REST"), false);
    }
}
