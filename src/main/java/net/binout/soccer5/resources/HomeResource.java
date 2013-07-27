package net.binout.soccer5.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * User: binout
 * Date: 26/07/13
 */
@Path("/")
public class HomeResource {

    @GET
    public Response home() {
        return Response.ok("Come and play Soccer !").build();
    }
}
