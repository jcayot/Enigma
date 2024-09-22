package com.cayot.enigma.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SubstitutionPartTest {
    @Test
    fun testSubstitutionPartCreationWithDifferentSizedArrays() {
        val entryArray = charArrayOf('A', 'B', 'C')
        val substitutionArray = charArrayOf('B', 'C', 'D', 'A')

        val exception = assertThrows<IllegalArgumentException> {
            Reflector.make(entryArray, substitutionArray)
        }
        assertEquals("Entry and substitution arrays must be the same size", exception.message)
    }

    @Test
    fun testSubstitutionPartCreationWithDuplicateEntryCharacters() {
        val entryArray = charArrayOf('A', 'B', 'C', 'A')
        val substitutionArray = charArrayOf('B', 'C', 'D', 'E')

        val exception = assertThrows<IllegalArgumentException> {
            Reflector.make(entryArray, substitutionArray)
        }
        assertEquals("Entry array contain two identical characters", exception.message)
    }

    @Test
    fun testSubstitutionPartCreationWithDuplicateSubstitutionCharacters() {
        val entryArray = charArrayOf('A', 'B', 'C', 'D')
        val substitutionArray = charArrayOf('B', 'C', 'D', 'D')

        val exception = assertThrows<IllegalArgumentException> {
            Reflector.make(entryArray, substitutionArray)
        }
        assertEquals("Substitution array contain two identical characters", exception.message)
    }

    @Test
    fun tesSubstitutionPartCreationWithNonMatchingEntrySubstitutionCharacters() {
        val entryArray = charArrayOf('A', 'B', 'C', 'D')
        val substitutionArray = charArrayOf('E', 'F', 'G', 'H')

        val exception = assertThrows<IllegalArgumentException> {
            Reflector.make(entryArray, substitutionArray)
        }
        assertEquals("Entry and substitution arrays must contain the same characters", exception.message)
    }

    @Test
    fun testEncodeWithValidCharacter() {
        val entryArray = charArrayOf('A', 'B', 'C', 'D')
        val substitutionArray = charArrayOf('D', 'C', 'B', 'A')
        val substitutionPart = Reflector.make(entryArray, substitutionArray)

        val encodedChar = substitutionPart.encode('A', false)
        assertEquals('D', encodedChar)
    }

    @Test
    fun testEncodeWithValidCharacterInReverse() {
        val entryArray = charArrayOf('A', 'B', 'C', 'D')
        val substitutionArray = charArrayOf('D', 'C', 'B', 'A')
        val substitutionPart = Reflector.make(entryArray, substitutionArray)

        val encodedChar = substitutionPart.encode('A', true)
        assertEquals('D', encodedChar)
    }

    @Test
    fun testEncodeWithInvalidCharacter() {
        val entryArray = charArrayOf('A', 'B', 'C', 'D')
        val substitutionArray = charArrayOf('D', 'C', 'B', 'A')
        val substitutionPart = Reflector.make(entryArray, substitutionArray)

        val exception = assertThrows<IllegalArgumentException> {
            substitutionPart.encode('E', false)
        }
        assertEquals("Character E not in set", exception.message)
    }
}