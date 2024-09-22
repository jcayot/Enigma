package com.cayot.enigma.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RotorTest {

    @Test
    fun testValidRotorCreationWithStandardWiring() {
        val rotor = Rotor.make(StandardRotorWiring.I)
        assertNotNull(rotor)
    }

    @Test
    fun testValidRotorCreationWithSubstitutionArray() {
        val substitutionArray = charArrayOf('A', 'B', 'C', 'D')
        val notch = "D".toCharArray()
        val rotor = Rotor.make(substitutionArray, notch)
        assertNotNull(rotor)
    }

    @Test
    fun testInvalidRotorCreationWithTooLongSubstitutionArray() {
        val substitutionArray = "QWERTYUIOPASDFGHJKLZXCVBNM123456798".toCharArray()
        val notch = "D".toCharArray()
        val exception = assertThrows<IllegalArgumentException> {
            Rotor.make(substitutionArray, notch)
        }
        assertEquals("Entry arrays are only generated for size upto 26", exception.message)
    }

    @Test
    fun testValidRotorCreation() {
        val entryArray = charArrayOf('A', 'B', 'C', 'D')
        val substitutionArray = charArrayOf('B', 'C', 'D', 'A')

        val rotor = Rotor.make(entryArray, substitutionArray)
        assertNotNull(rotor)
    }

    @Test
    fun testInvalidRotorWithNotchNotInArray() {
        val substitutionArray = charArrayOf('A', 'B', 'C')
        val notch = "D".toCharArray()
        val exception = assertThrows<IllegalArgumentException> {
            Rotor.make(substitutionArray, notch)
        }
        assertEquals("Notch must be contained in entry array", exception.message)
    }

    @Test
    fun incrementOffset_startOfAlphabet() {
        val rotor = Rotor.make(StandardRotorWiring.I)
        rotor.offset = 0

        val result = rotor.incrementOffset()
        assertEquals(1, rotor.offset)
        assertFalse(result, "Expected that notch is not passed when offset is at start of alphabet")
    }

    @Test
    fun incrementOffset_endOfAlphabet() {
        val rotor = Rotor.make(StandardRotorWiring.I)
        rotor.offset = 25

        val result = rotor.incrementOffset()
        assertEquals(0, rotor.offset)
        assertFalse(result, "Expected that notch is not passed even when reached end of alphabet")
    }

    @Test
    fun incrementOffsetOnNotch() {
        val rotor = Rotor.make(StandardRotorWiring.I)
        rotor.offset = 16

        val result = rotor.incrementOffset()
        assertEquals(17, rotor.offset)
        assertTrue(result, "Expected that notch is passed when current offset is on notch")
    }

    @Test
    fun isOnNotchNotOnNotch() {
        val rotor = Rotor.make(StandardRotorWiring.I)
        rotor.offset = 0

        val result = rotor.isOnNotch()

        assertFalse(result, "Expected that rotor is not on notch when offset is not on notch character")
    }

    @Test
    fun isOnNotchOnNotch() {
        val rotor = Rotor.make(StandardRotorWiring.I)
        rotor.offset = 16

        val result = rotor.isOnNotch()

        assertTrue(result, "Expected that rotor is on notch when offset is on notch character")
    }

    @Test
    fun testEncodeWithNoReverse() {
        val rotor = Rotor.make(StandardRotorWiring.I)
        rotor.offset = 0

        val result = rotor.encode('A', false)

        assertEquals('E', result)
    }

    @Test
    fun testEncodeWithReverse() {
        val rotor = Rotor.make(StandardRotorWiring.I)
        rotor.offset = 0

        val result = rotor.encode('A', true)

        assertEquals('U', result)
    }

    @Test
    fun testEncodeWithOffset() {
        val rotor = Rotor.make(StandardRotorWiring.I)
        rotor.offset = 10

        val result = rotor.encode('A', false)

        assertEquals('D', result)
    }

    @Test
    fun testEncodeWithNonExistentCharacter() {
        val rotor = Rotor.make(StandardRotorWiring.I)
        rotor.offset = 0

        val exception = assertThrows<IllegalArgumentException> {
            rotor.encode('#', false)
        }

        assertEquals("Character # not in set", exception.message)
    }
}
