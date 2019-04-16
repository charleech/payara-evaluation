package app.github.charleech.ft;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;

/**
 * <p>
 * This is a concrete implementing class which provides the feature as a timeout
 * {@code JAX-RS} resources.
 * </p>
 * @author charlee.ch
 *
 */
@Path("/ft")
@ApplicationScoped
public class MyCircuitBreakerResource {

    /**
     *  The circuit breaker resource.
     *
     * @return The response
     * @since 1.0.0
     */
    @GET
    @Path("/circuit")
    @CircuitBreaker
    public Response alwaysFail() {
        throw new WebApplicationException(
                      Response.serverError().
                               entity("This is always fail.").
                               build()
                  );
    }
}
