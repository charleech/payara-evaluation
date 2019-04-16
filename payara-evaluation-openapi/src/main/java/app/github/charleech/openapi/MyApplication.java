package app.github.charleech.openapi;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ApplicationPath;

import app.github.charleech.base.jaxrs.JaxRsApplicationBase;

/**
 * This is a concrete implementing class which provides the feature for
 * initiating the {@code JAX-RS} application.
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 * @see JaxRsApplicationBase
 */
@ApplicationPath("/api")
public class MyApplication extends JaxRsApplicationBase {

    @Override
    protected List<Class<?>> getRegistering() {
        List<Class<?>> classes = null;
        try {

            classes = new ArrayList<>();

            classes.add(MyLocalResource.class);
            classes.add(MyAnnotatedResource.class);
            classes.add(MyOuterResource.class);

            return classes;
        } finally {
            classes = null;
        }
    }

}
