package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Simple - name of method without parenthesis '()'
//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)

// class name and method name
//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestMethodOrder(MethodOrderer.Random.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoUtilsTest {
    DemoUtils demoUtils;

    @Test
    @DisplayName("Multiply")
    @Order(2)
    void testMultiply() {
       assertEquals(12, demoUtils.multiply(4,3), "4*3 must be 12");
    }

    @Test
    @DisplayName("Throws and Does not Throw")
    @Order(1)
    void testThrowsAndDoesNotThrow() {
        assertThrows(Exception.class, () -> { demoUtils.throwException(-1); }, "Should throw exception");
        assertDoesNotThrow(() -> { demoUtils.throwException(9); }, "Should not throw exception");
    }

    @Test
    @DisplayName("Lines match Equals")
    @Order(2)
    void testLinesMatch() {
        List<String> theList = List.of("luv", "2", "code");
// string list match
        assertLinesMatch(theList, demoUtils.getAcademyInList(),
                "Lines should match");
    }

    @Test
    @DisplayName("Iterable Equals")
    @Order(3)
    void testIterableEquals() {
        List<String> theList = List.of("luv", "2", "code");
// object deeply equal
        assertIterableEquals(theList, demoUtils.getAcademyInList(),
                "Expected list should be same as actual list");
    }

    @Test
    @DisplayName("Array Equals")
    @Order(4)
    void testArrayEquals() {
        String[] stringArray = {"A", "B", "C"};

        assertArrayEquals(stringArray, demoUtils.getFirstThreeLettersOfAlphabet(),
                "Arrays should be the same");
    }

    @Test
    @DisplayName("Same and Not Same")
    @Order(5)
    void testSameAndNotSame() {
//        String str = "luv2Code Academy";
        String str = "luv2code";

    assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Objects should refer to same object");
   assertNotSame(str, demoUtils.getAcademy(), "Object should not refer to object");
    }

    @Test
    @DisplayName("Greater and Not Greater")
    @Order(6)
    void testGreaterAndNotGreater() {
        int gradeOne = 10;
        int gradeTwo = 5;
        assertTrue(demoUtils.isGreater(gradeOne,gradeTwo), "This should return true");
        assertFalse(demoUtils.isGreater(gradeTwo, gradeOne), "This should return false");
    }

    @Test
    @DisplayName("Equals and Not Equals")
    @Order(7)
    void testEqualsAndNotEquals() {
//    void test_Equals_And_Not_Equals() {
        System.out.println("Running test: testEqualsAndNotEquals ");
        assertEquals(6,demoUtils.add(2,4), "2+4 must be 6");
        assertNotEquals(6,demoUtils.add(1,9), "1+9 must not be 6");
    }

    @Test
    @DisplayName("Null and Not Null")
    @Order(-8)
    void testNullAndNotNull() {
//        void test_Null_And_Not_Null() {
        System.out.println("Running test: testNullAndNotNull ");

        String str1 = null;
        String str2 = "test";

        assertNull(demoUtils.checkNull(str1));
        assertNotNull(demoUtils.checkNull(str2));

    }

    @Test
    @DisplayName("Timeout")
    @Order(9)
    void testTimeout() {
        assertTimeoutPreemptively(Duration.ofSeconds(3),
                () -> { demoUtils.checkTimeout(); },
                "Method should execute in 3 seconds");
    }

    @BeforeEach
    void setupBeforeEach() {
        demoUtils = new DemoUtils();
        System.out.println("@BeforeEach executes before the execution of each test method");
    }

    @AfterEach
    void tearDownAfterEach() {
        System.out.println("Running @AfterEach\n");
    }

    @BeforeAll
    static void setupBeforeAllClass() {
        System.out.println("Running @BeforeAll");
    }

    @AfterAll
    static void tearDownAfterAll() {
        System.out.println("Running @AfterAll");
    }


}
