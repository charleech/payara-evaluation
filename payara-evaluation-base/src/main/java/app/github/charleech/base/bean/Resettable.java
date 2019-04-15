package app.github.charleech.base.bean;

import java.io.Serializable;

/**
 * <p>
 * This is an interface which describes the feature for resetting the state.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Resettable extends Serializable {
    /**
     * Reset the state.
     *
     * @since 1.0.0
     */
    void reset();
}
