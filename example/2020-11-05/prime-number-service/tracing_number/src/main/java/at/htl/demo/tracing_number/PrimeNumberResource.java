package at.htl.demo.tracing_number;

import java.util.concurrent.atomic.LongAccumulator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;


@Path("/resteasy/hello")
public class PrimeNumberResource {

    private static final Logger LOG = Logger.getLogger(PrimeNumberResource.class);

    private final LongAccumulator highestPrime = new LongAccumulator(Long::max, 0);

    @GET
    @Path("/{number}")
    @Produces("text/plain")
    public Response checkIfPrime(@PathParam("number") long number) {
        LOG.info("hello");
        return Response.ok(getPrimeStatus(number)).build();
    }

    String getPrimeStatus(long number){
        if (number < 1) {
            return "Only natural numbers can be prime numbers.";
        }
        if (number == 1) {
            return "1 is not prime.";
        }
        if (number == 2) {
            return "2 is prime.";
        }
        if (number % 2 == 0) {
            return number + " is not prime, it is divisible by 2.";
        }


            for (long i = 3; i < Math.floor(Math.sqrt(number)) + 1; i = i + 2) {
                if (number % i == 0) {
                    return number + " is not prime, is divisible by " + i + ".";
                }
            }
            highestPrime.accumulate(number);
            return number + " is prime.";
    }

    /**
     * This method is called by the registered {@code highest.prime.number} gauge.
     * @return the highest observed prime value
     */
    long highestObservedPrimeNumber() {
        return highestPrime.get();
    }
}