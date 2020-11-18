package at.htl.demo.tracing_generator;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/generate")
public class GeneratorResource {

    private static final Logger LOG = Logger.getLogger(GeneratorResource.class);

    @Inject
    @RestClient
    PrimeService primeService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String generate() {
        var temp = primeService.getPrimeNumberStatus(System.currentTimeMillis());
        Message ms = new Message();
        ms.setMessage(temp);
        ms.persist();
        LOG.info("generate");
        return temp.toString();
    }
}