package app.github.charleech.conf;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.config.spi.ConfigSource;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * This is a concrete implementing class which provides the feature as a simple
 * {@code JAX-RS} resources.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 */
@Slf4j
@Path("/config")
@ApplicationScoped
public class MyConfigResource {

    /**
     * This is a constant which represents the configuration key as {@value}.
     *
     * @since 1.0.0
     */
    private static final String CONF_KEY = "my.name";

    /**
     * This is a constant which represents the default value as {@value}.
     *
     * @since 1.0.0
     */
    private static final String DEFAULT_VALUE = "unknown";

    /**
     * This is a variable which represents the configuration.
     *
     * @since 1.0.0
     */
    @Inject
    @ConfigProperty(
        name         = MyConfigResource.CONF_KEY,
        defaultValue = MyConfigResource.DEFAULT_VALUE
    )
    private Provider<String> myValue;

    /**
     *  The circuit breaker resource.
     *
     * @return The response
     * @since 1.0.0
     */
    @GET
    public Response getConfig() {
        return Response.ok().entity(this.getAllConfigSources()).build();
    }

    /**
     * Get all config from all config source.
     *
     * @since 1.0.0
     * @return
     */
    protected String getAllConfigSources() {
        StringBuilder bldr = null;
        try {
            bldr = new StringBuilder();

            for (final ConfigSource cs : ConfigProvider.getConfig().
                                                  getConfigSources()) {
                bldr.append("configName=").
                     append(cs.getName()).
                     append(", ").
                     append("configOrdinal=").
                     append(cs.getOrdinal()).
                     append("\r\n");


//                bldr.append(">>>>\r\n");
//                for (final Map.Entry<String, String> entry
//                        : cs.getProperties().entrySet()) {
//                    bldr.append(entry.getKey()).
//                         append("=").
//                         append(entry.getValue()).
//                         append("\r\n");
//                }
//                bldr.append("<<<<\r\n");
            }

            return bldr.toString();

        } finally {
            bldr = null;
        }
    }

    /**
     * Refresh config.
     *
     * @return The result
     * @since 1.0.0
     */
    protected String refreshConfig() {
        String value1 = null;
        String value2 = null;
        String value3 = null;
        String entity = null;
        try {
            value1 = this.myValue.get();
            System.setProperty(MyConfigResource.CONF_KEY, "system-value1");
            value2 = this.myValue.get();
            System.setProperty(MyConfigResource.CONF_KEY, "system-value2");
            value3 = this.myValue.get();

            entity = new StringBuilder().
                         append("value1=").append(value1).append(",").
                         append("value2=").append(value2).append(",").
                         append("value3=").append(value3).
                         toString();

            return entity;

        } finally {
            value1 = null;
            value2 = null;
            value3 = null;
            entity = null;
        }
    }
}
