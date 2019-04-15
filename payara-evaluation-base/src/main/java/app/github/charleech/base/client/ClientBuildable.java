package app.github.charleech.base.client;

import java.io.Serializable;

/**
 * <p>
 * This is an interface which describes the feature for creating the
 * {@code Microprofie: Rest Client}.
 * It is a {@code CDI: ApplicationScoped} bean.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ClientBuildable extends Serializable {
    /**
     * Create the {@code Microprofie: Rest Client}.
     *
     * @param <T>
     *            The {@code Microprofie: Rest Client} interface.
     * @param baseUrl
     *            The base url
     * @param resetClient
     *            The {@code Microprofie: Rest Client} interface.
     * @return The {@code Microprofie: Rest Client}
     * @since 1.0.0
     */
    <T> T produce(final String   baseUrl,
                  final Class<T> resetClient);
}
