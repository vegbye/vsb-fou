package vsb.fou.rest.jersey.common;

import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import java.io.IOException;
import java.util.logging.Logger;

public class VsbLoggingFilter extends LoggingFilter {

    public VsbLoggingFilter() {
        super(Logger.getLogger("TRANSACTION.REST"), true);
    }

    @Override
    public void filter(ClientRequestContext requestContext,
                       ClientResponseContext responseContext)
            throws IOException {
        System.out.println("requestContext.getPropertyNames() = " + requestContext.getPropertyNames());
        System.out.println("requestContext.getEntity() = " + requestContext.getEntity());
        for (String key : requestContext.getPropertyNames()) {
            Object val = requestContext.getProperty(key);
            System.out.println(key + "=>" + val);
        }
        super.filter(requestContext, responseContext);
    }
}
