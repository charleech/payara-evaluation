package test.app.github.charleech.base.bean;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * <p>
 * This is a concrete implementing class which provides the features as a test
 * suite for unit testing the customized {@code microprofile: fault tolerance}.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(Suite.class)
@SuiteClasses({
    DataBeanTester.class,
    OuterDataBeanTester.class
})
public class DataBeanTestSuite {

}
