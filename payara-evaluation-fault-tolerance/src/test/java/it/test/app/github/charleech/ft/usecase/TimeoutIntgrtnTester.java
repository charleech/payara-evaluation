package it.test.app.github.charleech.ft.usecase;

import java.net.URL;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.BDDAssertions;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import app.github.charleech.base.client.ClientBuildable;
import it.test.app.github.charleech.ft.client.MyTimeoutResourceClient;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * This is a concrete implementing class which provides the feature for testing
 * the  {@code Microprofile: OpenAPI}.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@RunWith(Arquillian.class)
public class TimeoutIntgrtnTester {

    /**
     * This is a variable which represents the {@link MockitoRule}.
     *
     * @since 1.0.0
     */
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    /**
     * This is a variable which represents the {@link SystemOutRule}.
     *
     * @since 1.0.0
     */
    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().
                                             enableLog().
                                             muteForSuccessfulTests();

    /**
     * This is a variable which represent the {@code Arquillian} deployment
     * URL.
     *
     * @since 1.0.s0
     */
    @ArquillianResource
    private URL basicUrl;

    /**
     * This is a variable which represents the {@link ClientBuildable}.
     *
     * @since 1.0.0
     */
    @Inject
    private ClientBuildable bldr;

    /**
     * This is a success test case when hello.
     *
     * @since 1.0.0
     */
    @Test
    @InSequence(1)
    public void whenAccessTimeoutResource() {
        Throwable               th     = null;
        WebApplicationException ex     = null;
        try {

            final MyTimeoutResourceClient client =
                    this.bldr.produce(this.basicUrl.toString() + "api",
                                      MyTimeoutResourceClient.class);

            th = Assertions.catchThrowable(() -> client.alwaysFail());

            BDDAssertions.then(th).
                          as("The exception must be thrown").
                          isNotNull().
                          isExactlyInstanceOf(WebApplicationException.class);

            ex = WebApplicationException.class.cast(th);

            BDDAssertions.then(ex.getResponse().readEntity(String.class)).
                          as("The root cause must be valid.").
                          contains("sleep interrupted");

        } finally {
            th = null;
            ex = null;
        }
    }

}
