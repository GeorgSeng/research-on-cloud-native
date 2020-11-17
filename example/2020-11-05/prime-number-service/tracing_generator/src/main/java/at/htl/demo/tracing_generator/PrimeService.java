package at.htl.demo.tracing_generator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

//@Path("/v2")
@RegisterRestClient(configKey = "prime-api")
public interface PrimeService {
    @GET
    @Path("/resteasy/hello/{number}")
    @Produces(MediaType.TEXT_PLAIN)
    String getPrimeNumberStatus(@PathParam long number);
}