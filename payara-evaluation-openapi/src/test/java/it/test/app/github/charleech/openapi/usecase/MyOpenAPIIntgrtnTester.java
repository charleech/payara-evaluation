package it.test.app.github.charleech.openapi.usecase;

import java.net.URL;

import javax.inject.Inject;

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
import it.test.app.github.charleech.openapi.client.MPRestClient;
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
public class MyOpenAPIIntgrtnTester {

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
    public void whenGetOpenAPI() {
        MPRestClient client = null;
        String       json   = null;
        try {

            client = this.bldr.produce(this.basicUrl.toString(),
                                       MPRestClient.class);

            json   = client.getOpenAPI();

            MyOpenAPIIntgrtnTester.log.info("The open api is\r\n{}",
                                            json);

        } finally {
            client = null;
            json   = null;
        }
    }

}
