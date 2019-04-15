package app.github.charleech.base.jaxrs;

import java.util.List;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.message.MessageProperties;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.CsrfProtectionFilter;
import org.glassfish.jersey.server.filter.EncodingFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * This is a base class which provides the feature for initiating the
 * {@code JAX-RS} application.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 * @see ResourceConfig
 */
@Slf4j
@SuppressWarnings({
    "PMD.UnusedPrivateField",
    "PMD.SingularField"
})
public abstract class JaxRsApplicationBase extends ResourceConfig {

    /**
     * This is a constructor which initiates the {@code JAX-RS} application.
     *
     * @since 1.0.0
     */
    public JaxRsApplicationBase() {
        this.registerEngine();
    }

    /**
     * This is a callback method which the derived class should presents the
     * to be registering resources.
     *
     * @return The registering resources
     * @since 1.0.0
     */
    protected abstract List<Class<?>> getRegistering();

    /**
     * Register the {@code JAX-RS} server side engine.
     *
     * @since 0.0.3
     */
    private void registerEngine() {

        /*
         * The Microprofile: Open API does not support the CDI during the
         * scanning, all implemented here must not be a CDI.
         */

        //Register the Moxy to be a JAXB implementation.
        this.register(MoxyXmlFeature.class);
        this.register(MoxyJsonFeature.class);

        this.register(JsonMoxyConfigContextResolver.class);

        this.register(MultiPartFeature.class);

        this.register(EncodingFilter.class);
        this.register(CsrfProtectionFilter.class);
        this.property(MessageProperties.XML_FORMAT_OUTPUT,
                      true);

        for (final Class<?> register : this.getRegistering()) {
            this.register(register);
        }
    }
}
