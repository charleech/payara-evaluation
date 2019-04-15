package app.github.charleech.base.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.glassfish.jersey.client.filter.CsrfProtectionFilter;
import org.glassfish.jersey.client.filter.EncodingFilter;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.message.MessageProperties;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;

import app.github.charleech.base.jaxrs.JsonMoxyConfigContextResolver;

/**
 * <p>
 * This is a concrete implementing class which provides the feature described at
 * {@link ClientBuildable}.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 * @see ClientBuildable
 */
@ApplicationScoped
public class ClientBuilder implements ClientBuildable {

    /**
     * This is a default serial version {@code UID} as {@value}.
     *
     * @since 1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a constant which represents the logging payload size as {@value}.
     *
     * @since 1.0.0
     */
    private static final int LOG_SIZE = 10000;

    @Override
    public <T> T produce(final String baseUrl, final Class<T> resetClient) {
        T   cli = null;
        URL url = null;
        try {

            url = new URL(baseUrl);

            cli = RestClientBuilder.newBuilder().
                                    register(EncodingFilter.class).
                                    register(CsrfProtectionFilter.class).
                                    register(JsonMoxyConfigContextResolver.
                                                 class).
                                    register(MoxyXmlFeature.class).
                                    register(MoxyJsonFeature.class).
                                    register(MultiPartFeature.class).
                                    property(MessageProperties.
                                                 XML_FORMAT_OUTPUT,
                                             true).
                                    register(SimpleRestExceptionMapper.class).
                                    register(this.getLoggingFeature()).
                                    baseUrl(url).
                                    build(resetClient);
            return cli;
        } catch (final MalformedURLException e) {
            throw new IllegalStateException("Cannote produce rest client." ,
                                            e);
        } finally {
            cli = null;
            url = null;
        }
    }

    /**
     * Get the logging feature.
     *
     * @return The logging feature
     * @since 1.0.0
     */
    private LoggingFeature getLoggingFeature() {
        return new LoggingFeature(
                           Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                           Level.INFO,
                           LoggingFeature.Verbosity.PAYLOAD_ANY,
                           ClientBuilder.LOG_SIZE);
    }

}
