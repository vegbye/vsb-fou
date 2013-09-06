package vsb.fou.rest.jersey.server;

import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;

/**
 * @author Vegard S. Bye
 */
public class VsbServerErrorException extends ServerErrorException {

    public VsbServerErrorException(String msg, Response.Status status, Throwable cause) {
        super(msg, status, cause);
    }
}
