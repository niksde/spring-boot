package com.luv2code.junitdemo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

class ConditionalTest {
    @Test
    @Disabled("Don't run until JIRA #123 resolved")
    void basicTest() {
        // execute method and perform asserts
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testForWindowsOnly() {
        // execute method and perform asserts
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testForMacOnly() {
        // execute method and perform asserts
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.WINDOWS})
    void testForMacAndWindowsOnly() {
        // execute method and perform asserts
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void testForLinuxOnly() {
        // execute method and perform asserts
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void testForJavaVersion17Only() {
        // execute method and perform asserts
    }

    @Test
    @EnabledOnJre(JRE.JAVA_21)
    void testForJavaVersion21Only() {
        // execute method and perform asserts
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_17, max = JRE.JAVA_21)
    void testForJavaVersionRangeOnly() {
        // execute method and perform asserts
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_17)
    void testForJavaVersionMinRangeOnly() {
        // execute method and perform asserts
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "NIKSDE_ENV", matches = "DEV")
    void testOnlyForDevEnvironment() {
        // execute method and perform asserts
    }

    @Test
    // regex in match is accepted
    @EnabledIfSystemProperty(named="NIKSDE_SYS_PROP",matches = "CI_CD_DEPLOY")
    void testForOnlySysyemProperty() {
        // execute method and perform asserts
    }
}
