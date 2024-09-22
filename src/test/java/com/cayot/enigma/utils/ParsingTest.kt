package com.cayot.enigma.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParsingTest {
    @Test
    fun testParseNumericalInputValidInput() {
        val input = "5"
        val minValue = 1
        val maxValue = 10
        val result = Parsing.parseNumericalInput(input, minValue, maxValue)
        Assertions.assertEquals(5, result)
    }

    @Test
    fun testParseNumericalInputMinimumBoundary() {
        val input = "1"
        val minValue = 1
        val maxValue = 10
        val result = Parsing.parseNumericalInput(input, minValue, maxValue)
        Assertions.assertEquals(1, result)
    }

    @Test
    fun testParseNumericalInputMaximumBoundary() {
        val input = "10"
        val minValue = 1
        val maxValue = 10
        val result = Parsing.parseNumericalInput(input, minValue, maxValue)
        Assertions.assertEquals(10, result)
    }

    @Test
    fun testParseNumericalInputBelowMinimumBoundary() {
        val input = "0"
        val minValue = 1
        val maxValue = 10
        val exception = assertThrows<IllegalArgumentException> {
            Parsing.parseNumericalInput(input, minValue, maxValue)
        }
        Assertions.assertEquals("Input must be between 1 and 10 included.", exception.message)
    }

    @Test
    fun testParseNumericalInputAboveMaximumBoundary() {
        val input = "11"
        val minValue = 1
        val maxValue = 10
        val exception = assertThrows<IllegalArgumentException> {
            Parsing.parseNumericalInput(input, minValue, maxValue)
        }
        Assertions.assertEquals("Input must be between 1 and 10 included.", exception.message)
    }

    @Test
    fun testParseNumericalInputNonNumericInput() {
        val input = "abc"
        val minValue = 1
        val maxValue = 10
        val exception = assertThrows<NumberFormatException> {
            Parsing.parseNumericalInput(input, minValue, maxValue)
        }
    }

    @Test
    fun testParseNumericalInputEmptyInput() {
        val input = ""
        val minValue = 1
        val maxValue = 10
        val exception = assertThrows<NumberFormatException> {
            Parsing.parseNumericalInput(input, minValue, maxValue)
        }
    }
}