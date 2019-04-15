package app.github.charleech.base.jaxrs;

import javax.ws.rs.ext.ContextResolver;

import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;

/**
 * <p>
 * This is a concrete implementing class which provides the feature described at
 * {@link ContextResolver} as a specific for configuring the JSON with MOXy.
 * </p>
 * <p>
 * This is a customization which overrides the Jersey default auto-discovery. If
 * this is only for the JSON pretty format output. I many not be worth enough.
 * Please use with high considering.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 * @see ContextResolver
 * @see <a href="http://bit.ly/16Zhbpi">The Jersey with JSON and MOXy</a>
 * @see <a href="http://bit.ly/16Zhe4c">MOXy as Your JAX-RS JSON Provider -
 *      Server Side</a>
 * @see <a href="http://bit.ly/16ZheS1">The Jersey with JSON and MOXy</a>
 */
@SuppressWarnings({
    "PMD.UnusedPrivateField",
    "PMD.SingularField"
})
public class JsonMoxyConfigContextResolver
  implements ContextResolver<MoxyJsonConfig> {

    @Override
    public MoxyJsonConfig getContext(final Class<?> type) {
        MoxyJsonConfig configuration = null;
        try {
            configuration  = new MoxyJsonConfig();

            configuration.setAttributePrefix("").
                          setValueWrapper("value").
                          property(JAXBContextProperties.
                                      JSON_WRAPPER_AS_ARRAY_NAME,
                                   true).
                          property(JAXBContextProperties.JSON_INCLUDE_ROOT,
                                   false).
                          setFormattedOutput(true);

            return configuration;
        } finally {
            configuration = null;
        }
    }

}
