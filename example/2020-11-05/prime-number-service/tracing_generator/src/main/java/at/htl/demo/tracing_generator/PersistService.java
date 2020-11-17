package at.htl.demo.tracing_generator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

/*
@RegisterRestClient(configKey = "persist-api")
public class PersistService {

    @GET
    @Path("/resteasy/hello/")
    @Produces(MediaType.TEXT_PLAIN)
    String postPrimeNumberStatus(@PathParam String result);
}
*/
