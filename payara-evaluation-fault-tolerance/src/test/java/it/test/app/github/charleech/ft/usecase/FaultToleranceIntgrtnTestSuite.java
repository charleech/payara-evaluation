package it.test.app.github.charleech.ft.usecase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * <p>
 * This is a concrete implementing class which provides the features as a test
 * suite for unit testing the customized {@code Microprofile: OpenAPI}.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(Suite.class)
@SuiteClasses({
    TimeoutIntgrtnTester.class,
    CircuitBreakerIntgrtnTester.class
})
public class FaultToleranceIntgrtnTestSuite {

}
