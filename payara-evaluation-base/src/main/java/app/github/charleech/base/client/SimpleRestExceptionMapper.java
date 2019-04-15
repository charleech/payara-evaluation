package app.github.charleech.base.client;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * This is a concrete implementing class which provides the feature for handling
 * the {@code microprofile: rest client} exception.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 * @see ResponseExceptionMapper
 */
@Slf4j
@Provider
public class SimpleRestExceptionMapper
  implements ResponseExceptionMapper<WebApplicationException> {

    @Override
    public boolean handles(final int                            status,
                           final MultivaluedMap<String, Object> headers) {
        return status >= Response.Status.BAD_REQUEST.getStatusCode();
    }

    @Override
    public WebApplicationException toThrowable(final Response response) {
        return new WebApplicationException("Perform fail.",
                                           response);
    }

}
