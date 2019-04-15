package it.test.app.github.charleech.base.jaxrs;

import java.net.URL;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

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

import com.toomuchcoding.jsonassert.BDDJsonAssertions;
import com.toomuchcoding.jsonassert.JsonPath;

import app.github.charleech.base.bean.MySimpleDataBean;
import app.github.charleech.base.client.ClientBuildable;
import it.test.app.github.charleech.base.mock.MyResourceClient;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * This is a concrete implementing class which provides the feature for testing
 * the {@code JAX-RS base feature}
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@RunWith(Arquillian.class)
public class MyResourceIntgrtnTester {

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
    public void whenHelloWithDefault() {
        MyResourceClient client = null;
        MySimpleDataBean simple = null;
        try {

            client = this.bldr.produce(this.basicUrl.toString() + "api",
                                       MyResourceClient.class);
            simple = client.hello(null);

            BDDAssertions.then(simple.getMessage()).
                          as("The message must be valid.").
                          isEqualTo("Hello unknown");

        } finally {
            client = null;
            simple = null;
        }
    }

    /**
     * This is a success test case when hello.
     *
     * @since 1.0.0
     */
    @Test
    @InSequence(2)
    public void whenHelloWithName() {
        MyResourceClient client = null;
        MySimpleDataBean simple = null;
        try {

            client = this.bldr.produce(this.basicUrl.toString() + "api",
                                       MyResourceClient.class);
            simple = client.hello("Payara");

            BDDAssertions.then(simple.getMessage()).
                          as("The message must be valid.").
                          isEqualTo("Hello Payara");

        } finally {
            client = null;
            simple = null;
        }
    }

    /**
     * This is a failure test case when hello.
     *
     * @since 1.0.0
     */
    @Test
    @InSequence(101)
    public void whenHelloFailure() {
        Throwable               ex = null;
        WebApplicationException we = null;
        Response                rs = null;
        String                  js = null;
        try {

            final MyResourceClient client =
                    this.bldr.produce(this.basicUrl.toString() + "api",
                                       MyResourceClient.class);

            ex = Assertions.catchThrowable(() -> client.hello("EXCEPTION"));

            BDDAssertions.then(ex).
                          as("The exception must be thrown").
                          isNotNull().
                          isExactlyInstanceOf(WebApplicationException.class).
                          hasMessageContaining("Perform fail.");

            we = WebApplicationException.class.cast(ex);

            rs = we.getResponse();

            BDDAssertions.then(rs.getStatus()).
                          as("The failure response must be valid.").
                          isEqualTo(Response.Status.
                                             INTERNAL_SERVER_ERROR.
                                             getStatusCode());

            js = rs.readEntity(String.class);

            BDDJsonAssertions.then(JsonPath.builder(js)).
                              as("The message must be valid.").
                              field("message").
                              isEqualTo("This is a failure.");


        } finally {
            ex = null;
            we = null;
            rs = null;
            js = null;
        }
    }

}
