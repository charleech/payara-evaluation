package it.test.app.github.charleech.base.mock;

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

            classes.add(MyResource.class);

            return classes;
        } finally {
            classes = null;
        }
    }

}
