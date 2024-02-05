package com.luv2code.tdd;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FizzBuzzTest {
//    If number is divisible by 3, print Fizz
//    If number is divisible by 5, print Buzz
//    If number is divisible by 3 and 5, print FizzBuzz
//    If number is NOT divisible by 3 or 5, print the number
    @DisplayName("Divisible by Three")
    @Test
    @Order(1)
    void testDivisibleByThree(){
        String expected = "Fizz";
        assertEquals(expected, FizzBuzz.compute(3), "Should return Fizz");
    }

    @DisplayName("Divisible by Five")
    @Test
    @Order(2)
    void testDivisibleByFive(){
        String expected = "Buzz";
        assertEquals(expected, FizzBuzz.compute(5), "Should return Buzz");
    }

    @DisplayName("Divisible by Three and Five")
    @Test
    @Order(3)
    void testDivisibleByThreeAndFive() {
        String expected = "FizzBuzz";
        assertEquals(expected, FizzBuzz.compute(15), "Should return FizzBuzz");
    }

    @DisplayName("Not Divisible by Three or Five")
    @Test
    @Order(3)
    void testNotDivisibleByThreeOrFive() {
        String expected = "7";
        assertEquals(expected, FizzBuzz.compute(7), "Should return Number");
    }

    @DisplayName("Testing with CSV values")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvSource({"1, 1","3, Fizz","5, Buzz","15, FizzBuzz"})
    @Order(3)
    void testCsvDataFile(int value, String expected) {
        assertEquals(expected, FizzBuzz.compute(value));
    }

    @DisplayName("Testing with Small data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources = "/small-test-data.csv")
    @Order(4)
    void testSmallDataFile(int value, String expected) {
        assertEquals(expected, FizzBuzz.compute(value));
    }

    @DisplayName("Testing with Medium data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources = "/medium-test-data.csv")
    @Order(5)
    void testMediumDataFile(int value, String expected) {
        assertEquals(expected, FizzBuzz.compute(value));
    }

    @DisplayName("Testing with Large data file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources = "/large-test-data.csv")
    @Order(6)
    void testLargeDataFile(int value, String expected) {
        assertEquals(expected, FizzBuzz.compute(value));
    }


}
