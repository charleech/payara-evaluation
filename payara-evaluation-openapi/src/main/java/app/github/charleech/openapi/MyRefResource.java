package app.github.charleech.openapi;

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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import app.github.charleech.base.bean.MySimpleDataBean;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * This is a concrete implementing class which provides the feature as a hello
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
@Path("/ref")
@Consumes({
    MediaType.APPLICATION_JSON
})
@Produces({
    MediaType.APPLICATION_JSON
})
public class MyRefResource implements Serializable {

    /**
     * This is a default serial version {@code UID} as {@value}.
     *
     * @since 1.0.0
     */
    private static final long serialVersionUID = 1L;

    @GET
    @Path("/hello")
    @Operation(
        summary     = "Simple hello with reference data bean.",
        description = "Simple hello with reference data bean."
    )
    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "200",
                description  = "The hello response",
                content      = {
                    @Content(
                        mediaType = "application/json",
                        schema    = @Schema(
                            implementation = MySimpleDataBean.class
                        )
                    )
                }
            ),
            @APIResponse(
                responseCode = "500",
                description  = "The service failure.",
                content      = {
                    @Content(
                        mediaType = "application/json",
                        schema    = @Schema(
                            implementation = MySimpleDataBean.class
                        )
                    )
                }
            )
        }
    )
    public Response hello(@Parameter(
                              description = "The name",
                              required    = true,
                              schema      = @Schema(
                                  type   = SchemaType.STRING
                              )
                          )
                          @QueryParam("name")
                          @DefaultValue("unknown")
                          final String name) {
        return null;
    }

}
