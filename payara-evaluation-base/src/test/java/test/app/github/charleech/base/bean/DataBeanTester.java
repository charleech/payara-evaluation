package test.app.github.charleech.base.bean;

import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import app.github.charleech.base.bean.MySimpleDataBean;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * This is a concrete implementing class which provides the unit testing for
 * testing the {@code data bean base}.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public class DataBeanTester {

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
     * This is a success test case when resetting.
     *
     * @since 1.0.0
     */
    @Test
    public void whenReset() {
        MySimpleDataBean simple = null;
        try {

            simple = new MySimpleDataBean();
            simple.reset();

            BDDAssertions.then(simple.getCorrelationId()).
                          as("The resetting must be valid").
                          isNotNull().
                          isNotEmpty();

            BDDAssertions.then(simple.getMessage()).
                          as("The resetting must be valid").
                          isNull();

        } finally {
            simple = null;
        }
    }
}
