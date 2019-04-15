package it.test.app.github.charleech.base.mock;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import app.github.charleech.base.bean.MySimpleDataBean;

/**
 * <p>
 * This is an interface which describes the feature as a {@code REST client}
 * for the {@link it.test.app.github.charleech.base.mock.MyResource}.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 * @see Serializable
 * @see RegisterRestClient
 */
@ApplicationScoped
@RegisterRestClient
@Path("/myresource")
@Consumes({
    MediaType.APPLICATION_JSON
})
@Produces({
    MediaType.APPLICATION_JSON
})
public interface MyResourceClient {
    /**
     * This is a hello.
     *
     * @return The hello.
     * @since 1.0.0
     */
    @GET
    @Path("/hello")
    MySimpleDataBean hello(@QueryParam("name")
                           @DefaultValue("unknown")
                           final String name);
}
