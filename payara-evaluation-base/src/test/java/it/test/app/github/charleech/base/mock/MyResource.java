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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import app.github.charleech.base.bean.MySimpleDataBean;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * This is a concrete implementing class which provides the feature as a mocked
 * {@code JAX-RS} resources.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 * @see Serializable
 */
@Slf4j
@ApplicationScoped
@Path("/myresource")
@Consumes({
    MediaType.APPLICATION_JSON
})
@Produces({
    MediaType.APPLICATION_JSON
})
public class MyResource implements Serializable {

    /**
     * This is a default serial version {@code UID} as {@value}.
     *
     * @since 1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a constant which represents the failure name.
     *
     * @since 1.0.0
     */
    private static final String FAILURE = "EXCEPTION";

    /**
     * This is a hello.
     *
     * @return The hello.
     * @since 1.0.0
     */
    @GET
    @Path("/hello")
    public Response hello(@QueryParam("name")
                          @DefaultValue("unknown")
                          final String name) {
        MySimpleDataBean simple = null;
        Response.Status  status = null;
        try {

            simple = new MySimpleDataBean();
            simple.reset();

            if (MyResource.FAILURE.equals(name)) {
                status = Status.INTERNAL_SERVER_ERROR;
                simple.setMessage("This is a failure.");
            } else {
                status = Status.OK;
                simple.setMessage("Hello " + name);
            }

            return Response.status(status).
                            entity(simple).
                            build();
        } finally {
            simple = null;
            status = null;
        }
    }

}
