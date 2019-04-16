package app.github.charleech.ft;

import java.time.temporal.ChronoUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Timeout;

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
public class MyTimeoutResource {

    /**
     * The timeout resource.
     *
     * @return The response
     * @throws InterruptedException
     *             If there is any error
     * @since 1.0.0
     */
    @GET
    @Path("/timeout")
    @Timeout(
        value = 1000,
        unit  = ChronoUnit.MILLIS
    )
    public Response alwaysFail() throws InterruptedException {
        Thread.sleep(1500L);
        return Response.ok().build();
    }
}
