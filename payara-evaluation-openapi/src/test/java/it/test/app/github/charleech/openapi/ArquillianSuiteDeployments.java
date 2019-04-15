package it.test.app.github.charleech.openapi;

import java.io.File;
import java.io.IOException;

import javax.faces.application.ProjectStage;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * This is a concrete implementing class which provides the feature for
 * supporting the {@code JBoss: Arquillian - TestSuite}, the single deployment
 * across all {@code JUnit: TestSuite}.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 * @see <a href="http://bit.ly/1zlnssV">GitHub Ingwarsw:
 *      arquillian-suite-extension</a>
 */
@Slf4j
@ArquillianSuiteDeployment
public class ArquillianSuiteDeployments {
    /**
     * Create the {@code JBoss: Arquillian} archive for testing.
     *
     * @return The {@code JBoss: Arquillian} archive for testing
     * @throws IOException
     *             If there is any error during loading resource
     * @since 1.0.0
     * @see <a href="https://developer.jboss.org/thread/148260">How do I add a
     *      JAR to the Deployment archive?</a>
     * @see <a href="http://bit.ly/1U1ZFHC">How can I remove my Test Classes
     *      from my ShrinkWrap Archive</a>
     */
    @Deployment(name = "payara-evaluation-base")
    public static WebArchive createDeployment() throws IOException {

        ArquillianSuiteDeployments.setProjectStageAsUnitTest();

        /*
         * https://stackoverflow.com/questions/13001371/
         */

        final File[] libs =  Maven.resolver().
                                   loadPomFromFile("pom.xml").
                                   importDependencies(ScopeType.COMPILE,
                                                      ScopeType.RUNTIME).
                                   resolve().
                                   withTransitivity().
                                   asFile();

        return ShrinkWrap.create(WebArchive.class,
                                 "myweb.war").
                          addPackages(true,
                                      "app.github.charleech.openapi").
                          addPackages(true,
                                      "test.app.github.charleech.openapi").
                          addPackages(true,
                                      "it.test.app.github.charleech.openapi").
                          addAsWebInfResource(
                              new File("src/test/resources/META-INF/beans.xml"),
                              "beans.xml"
                          ).
                          addAsResource(
                              new File("src/test/resources/META-INF/microprofile-config.properties"),
                              "META-INF/microprofile-config.properties"
                          ).
                          addAsResource(
                              new File("src/test/resources/logback-test.xml"),
                              "logback-test.xml"
                          ).
                          addAsLibraries(libs);
    }


    /**
     * Set project stage to unit test.
     *
     * @since 1.0.0
     */
    private static void setProjectStageAsUnitTest() {
        System.setProperty("org.apache.deltaspike.ProjectStage",
                           ProjectStage.UnitTest.toString());
    }
}
