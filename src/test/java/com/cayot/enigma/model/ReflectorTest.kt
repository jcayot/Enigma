package com.cayot.enigma.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ReflectorTest {

    @Test
    fun testValidReflectorCreationWithStandardWiring() {
        val reflector = Reflector.make(StandardReflectorWiring.A)
        assertNotNull(reflector)
    }

    @Test
    fun testValidReflectorCreationWithEntryWiring() {
        val entryArray = charArrayOf('A', 'B', 'C', 'D')
        val reflector = Reflector.make(entryArray)
        assertNotNull(reflector)
    }

    @Test
    fun testInvalidReflectorCreationWithTooLongEntryWiring() {
        val entryArray = "QWERTYUIOPASDFGHJKLZXCVBNM123456798".toCharArray()
        val exception = assertThrows<IllegalArgumentException> {
            Reflector.make(entryArray)
        }
        assertEquals("Entry arrays are only generated size upto 26", exception.message)
    }

    @Test
    fun testValidReflectorCreation() {
        val entryArray = charArrayOf('A', 'B', 'C', 'D')
        val substitutionArray = charArrayOf('B', 'C', 'D', 'A')

        val reflector = Reflector.make(entryArray, substitutionArray)
        assertNotNull(reflector)
    }
}