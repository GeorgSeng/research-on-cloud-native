package at.htl.demo.tracing_persestance;

import org.eclipse.microprofile.openapi.annotations.Operation
import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.ws.rs.*
import javax.ws.rs.core.MediaType;

@Path("/resteasy")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
class PersestanceResource {

    @POST
    @Path("/persist/{message}")
    //@Consumes(MediaType.TEXT_PLAIN)
    //@Produces(MediaType.TEXT_PLAIN)
    @Operation(summary="Get all Fruits", description="Returns a a list with all fruites that are in the db")
    fun hello(@PathParam message: String ): String {
        val temp = Message();
        temp.message = message;
        temp.persist();
        return "hello";
    }
}