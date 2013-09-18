package vsb.fou.rest.jersey.server;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author Vegard S. Bye
 */
@javax.ws.rs.ext.Provider
public class VsbServerExceptionMapper implements ExceptionMapper<VsbServerException> {

    @Override
    public Response toResponse(VsbServerException e) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(e.getMessage()).type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
