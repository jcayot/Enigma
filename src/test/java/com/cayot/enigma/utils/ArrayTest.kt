package com.cayot.enigma.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ArrayTest {
    @Test
    fun testFirstIndexOfWhenElementIsAtBeginning() {
        val array = charArrayOf('A', 'B', 'C', 'D')
        assertEquals(0, Array.firstIndexOf(array, 'A'))
    }

    @Test
    fun testFirstIndexOfWhenElementIsInMiddle() {
        val array = charArrayOf('A', 'B', 'C', 'D')
        assertEquals(2, Array.firstIndexOf(array, 'C'))
    }

    @Test
    fun testFirstIndexOfWhenElementIsAtEnd() {
        val array = charArrayOf('A', 'B', 'C', 'D')
        assertEquals(3, Array.firstIndexOf(array, 'D'))
    }

    @Test
    fun testFirstIndexOfWhenElementIsNotPresent() {
        val array = charArrayOf('A', 'B', 'C', 'D')
        assertEquals(-1, Array.firstIndexOf(array, 'F'))
    }

    @Test
    fun testFirstIndexOfWhenArrayIsEmpty() {
        val array = charArrayOf()
        assertEquals(-1, Array.firstIndexOf(array, 'A'))
    }

    @Test
    fun testFirstIndexOfWhenArrayHasOneCharacter() {
        val array = charArrayOf('A')
        assertEquals(0, Array.firstIndexOf(array, 'A'))
        assertEquals(-1, Array.firstIndexOf(array, 'B'))
    }

    @Test
    fun testFirstIndexOfWhenArrayHasRepeatedCharacters() {
        val array = charArrayOf('A', 'A', 'A', 'A')
        assertEquals(0, Array.firstIndexOf(array, 'A'))
    }

    @Test
    fun testFirstIndexOfWhenArrayHasMixedCaseCharacters() {
        val array = charArrayOf('a', 'B', 'c', 'D')
        assertEquals(1, Array.firstIndexOf(array, 'B'))
        assertEquals(-1, Array.firstIndexOf(array, 'b'))
    }

    @Test
    fun testContainWhenPresent() {
        val array = charArrayOf('a', 'b', 'c', 'd', 'e')
        assertTrue(Array.contain(array, 'c'))
    }

    @Test
    fun testContainWhenNotPresent() {
        val array = charArrayOf('a', 'b', 'c', 'd', 'e')
        assertFalse(Array.contain(array, 'z'))
    }

    @Test
    fun testContainWhenEmptyArray() {
        val array = charArrayOf()
        assertFalse(Array.contain(array, 'a'))
    }

    @Test
    fun testContainWithSpecialCharacters() {
        val array = charArrayOf('@', '#', '$', '%', '^')
        assertTrue(Array.contain(array, '$'))
        assertFalse(Array.contain(array, '&'))
    }

    @Test
    fun testContainWithNumbers() {
        val array = charArrayOf('1', '2', '3', '4', '5')
        assertTrue(Array.contain(array, '3'))
        assertFalse(Array.contain(array, '0'))
    }

    @Test
    fun testContainDuplicateWhenNoDuplicates() {
        val array = charArrayOf('A', 'B', 'C', 'D')
        assertFalse(Array.containDuplicate(array))
    }

    @Test
    fun testContainDuplicateWhenHasDuplicates() {
        val array = charArrayOf('A', 'B', 'A', 'D')
        assertTrue(Array.containDuplicate(array))
    }

    @Test
    fun testContainDuplicateWhenAllSame() {
        val array = charArrayOf('A', 'A', 'A', 'A')
        assertTrue(Array.containDuplicate(array))
    }

    @Test
    fun testContainDuplicateWhenArrayIsEmpty() {
        val array = charArrayOf()
        assertFalse(Array.containDuplicate(array))
    }

    @Test
    fun testContainDuplicateWhenArrayHasOneCharacter() {
        val array = charArrayOf('A')
        assertFalse(Array.containDuplicate(array))
    }

    @Test
    fun testContainDuplicateWhenArrayHasDifferentCases() {
        val array = charArrayOf('a', 'A', 'b', 'B')
        assertFalse(Array.containDuplicate(array))
    }

    @Test
    fun testContainDuplicateWhenSpecialCharacters() {
        val array = charArrayOf('@', '#', '$', '@')
        assertTrue(Array.containDuplicate(array))
    }

    @Test
    fun testContainSameValuesWhenArraysAreEqual() {
        val array1 = charArrayOf('A', 'B', 'C', 'D')
        val array2 = charArrayOf('A', 'B', 'C', 'D')
        assertTrue(Array.containSameValues(array1, array2))
    }

    @Test
    fun testContainSameValuesWhenArraysAreDifferent() {
        val array1 = charArrayOf('A', 'B', 'C', 'D')
        val array2 = charArrayOf('E', 'F', 'G', 'H')
        assertFalse(Array.containSameValues(array1, array2))
    }

    @Test
    fun testContainSameValuesWhenArraysHaveSameCharactersInDifferentOrder() {
        val array1 = charArrayOf('A', 'B', 'C', 'D')
        val array2 = charArrayOf('D', 'C', 'B', 'A')
        assertTrue(Array.containSameValues(array1, array2))
    }

    @Test
    fun testContainSameValuesWhenArraysHaveDuplicates() {
        val array1 = charArrayOf('A', 'A', 'B', 'B')
        val array2 = charArrayOf('B', 'B', 'A', 'A')
        assertTrue(Array.containSameValues(array1, array2))
    }

    @Test
    fun testContainSameValuesWhenOneArrayIsEmpty() {
        val array1 = charArrayOf('A', 'B', 'C', 'D')
        val array2 = charArrayOf()
        assertFalse(Array.containSameValues(array1, array2))
    }

    @Test
    fun testContainSameValuesWhenBothArraysAreEmpty() {
        val array1 = charArrayOf()
        val array2 = charArrayOf()
        assertTrue(Array.containSameValues(array1, array2))
    }

    @Test
    fun testContainSameValuesWhenArraysHaveMixedCaseCharacters() {
        val array1 = charArrayOf('a', 'b', 'c', 'd')
        val array2 = charArrayOf('A', 'B', 'C', 'D')
        assertFalse(Array.containSameValues(array1, array2))
    }

    @Test
    fun testContainSameValuesWhenArray1IsSubsetOfArray2() {
        val array1 = charArrayOf('A', 'B')
        val array2 = charArrayOf('A', 'B', 'C')
        assertFalse(Array.containSameValues(array1, array2))
    }

    @Test
    fun testMakeAlphabeticalArrayForPositiveLength() {
        val length = 5
        val firstChar = 'A'
        val expectedArray = charArrayOf('A', 'B', 'C', 'D', 'E')
        val resultArray = Array.makeAlphabeticalArray(length, firstChar)
        assertArrayEquals(expectedArray, resultArray)
    }

    @Test
    fun testMakeAlphabeticalArrayForZeroLength() {
        val length = 0
        val firstChar = 'A'
        val expectedArray = charArrayOf()
        val resultArray = Array.makeAlphabeticalArray(length, firstChar)
        assertArrayEquals(expectedArray, resultArray)
    }

    @Test
    fun testMakeAlphabeticalArrayWithDifferentStartingChar() {
        val length = 4
        val firstChar = 'X'
        val expectedArray = charArrayOf('X', 'Y', 'Z', '[')
        val resultArray = Array.makeAlphabeticalArray(length, firstChar)
        assertArrayEquals(expectedArray, resultArray)
    }

    @Test
    fun testMakeAlphabeticalArrayForLowercase() {
        val length = 3
        val firstChar = 'm'
        val expectedArray = charArrayOf('m', 'n', 'o')
        val resultArray = Array.makeAlphabeticalArray(length, firstChar)
        assertArrayEquals(expectedArray, resultArray)
    }

    @Test
    fun testMakeAlphabeticalArrayForNonAlphabeticStartingChar() {
        val length = 3
        val firstChar = '1'
        val expectedArray = charArrayOf('1', '2', '3')
        val resultArray = Array.makeAlphabeticalArray(length, firstChar)
        assertArrayEquals(expectedArray, resultArray)
    }

    @Test
    fun testMakeAlphabeticalArrayForWrappingAtEndOfAlphabet() {
        val length = 5
        val firstChar = 'Y'
        val expectedArray = charArrayOf('Y', 'Z', '[', '\\', ']')
        val resultArray = Array.makeAlphabeticalArray(length, firstChar)
        assertArrayEquals(expectedArray, resultArray)
    }
}