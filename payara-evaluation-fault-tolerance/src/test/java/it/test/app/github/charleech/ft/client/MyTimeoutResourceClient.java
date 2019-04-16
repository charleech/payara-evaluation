package it.test.app.github.charleech.ft.client;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * <p>
 * This is an interface which describes the feature as a {@code REST client}
 * for the common {@code microprofile}.
 * </p>
 * <p>
 * It is registered at
 * {@code /src/test/resources/META-INF/microprofile-config.properties}.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 * @see RegisterRestClient
 */
@ApplicationScoped
@RegisterRestClient
@Path("/ft")
@Consumes({
    MediaType.APPLICATION_JSON
})
@Produces({
    MediaType.APPLICATION_JSON
})
public interface MyTimeoutResourceClient {

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
    Response alwaysFail();
}
