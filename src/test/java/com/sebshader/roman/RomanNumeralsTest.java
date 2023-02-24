
package com.sebshader.roman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class RomanNumeralsTest{
    @ParameterizedTest
    @DisplayName("fromInt")
    @MethodSource("provideInts")
    void toStringTest(int input, String expected) {
        RomanNumeral tester = new RomanNumeral(input);
        assertEquals(tester.toString(), expected);
    }
    private static Stream<Arguments> provideInts() {
        return Stream.of(
            Arguments.of(1, "I"),
            Arguments.of(4, "IV"),
            Arguments.of(88, "LXXXVIII"),
            Arguments.of(99, "XCIX"),
            Arguments.of(3006, "MMMVI")
        );
    }
    @ParameterizedTest
    @DisplayName("fromString")
    @MethodSource("provideStrings")
    void toStringTest(String input, int expected) {
        RomanNumeral tester = new RomanNumeral(input);
        assertEquals(tester.toInt(), expected);
    }
    private static Stream<Arguments> provideStrings() {
        return Stream.of(
            Arguments.of("I", 1),
            Arguments.of("IV", 4),
            Arguments.of("LXXXVIII", 88),
            Arguments.of("XCIX", 99),
            Arguments.of("CCCXXIX", 329),
            Arguments.of("CMLIX", 959),
            Arguments.of("CMLXXXIX", 989),
            Arguments.of("MMMVI", 3006)
        );
    }
    @ParameterizedTest
    @DisplayName("testIntExceptions")
    @MethodSource("provideIntExceptions")
    void intExceptionTest(int input) {
        assertThrows(NumberFormatException.class, () -> new RomanNumeral(input));
    }
    private static Stream<Arguments> provideIntExceptions() {
        return Stream.of(
            Arguments.of(4500),
            Arguments.of(0),
            Arguments.of(-1)
        );
    }
    @ParameterizedTest
    @DisplayName("testStringExceptions")
    @MethodSource("provideStringExceptions")
    void stringExceptionTest(String input) {
        assertThrows(NumberFormatException.class, () -> new RomanNumeral(input));
    }
    private static Stream<Arguments> provideStringExceptions() {
        return Stream.of(
            Arguments.of("IXIX"),
            Arguments.of("XIIX"),
            Arguments.of("XVV"),
            Arguments.of("IXIV"),
            Arguments.of("IVV"),
            Arguments.of("IVIV"),
            Arguments.of("VIV"),
            Arguments.of("IVI"),
            Arguments.of("IIII"),
            Arguments.of("VX"),
            Arguments.of("aI"),
            Arguments.of("1I"),
            Arguments.of("V1I")
        );
    }
}
