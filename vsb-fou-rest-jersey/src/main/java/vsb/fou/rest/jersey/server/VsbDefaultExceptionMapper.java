package vsb.fou.rest.jersey.server;

import vsb.fou.rest.jersey.api.VsbRestError;

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
        VsbRestError vsbRestError = new VsbRestError();
        vsbRestError.setErrorId(Long.toString(System.currentTimeMillis()));
        vsbRestError.setErrorMsg(e.toString());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(vsbRestError)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
