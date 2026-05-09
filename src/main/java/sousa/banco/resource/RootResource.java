package sousa.banco.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/")
public class RootResource {

    @GET
    public Response home() {
        return Response.seeOther(URI.create("/q/dev-ui/extensions")).build();
    }
}
