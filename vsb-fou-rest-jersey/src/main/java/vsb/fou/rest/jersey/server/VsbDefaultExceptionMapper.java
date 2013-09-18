package vsb.fou.rest.jersey.server;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author Vegard S. Bye
 */
@javax.ws.rs.ext.Provider
public class VsbDefaultExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(e.toString()).type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
