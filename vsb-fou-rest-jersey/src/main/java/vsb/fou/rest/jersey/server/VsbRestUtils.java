package vsb.fou.rest.jersey.server;

import javax.ws.rs.core.Response;
import java.net.URI;

public class VsbRestUtils {

    public static Response okResponse(Object entity) {
        return Response.ok().encoding("UTF-8").entity(entity).build();
    }

    public static Response createdResponse(Object entity, URI location) {
        return Response.created(location).entity(entity).build();
    }
}
