package com.cayot.enigma.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class PlugboardTest {

    @Test
    fun addLinkSuccess() {
        val testPlugboard = Plugboard(charArrayOf('A', 'B'))
        testPlugboard.addLink('A', 'B')
        assertEquals(Link('A', 'B'), testPlugboard.getLinks()[0])
    }

    @Test
    fun addLinkSameCharacterThrowIllegalArgumentException() {
        val testPlugboard = Plugboard(charArrayOf('A', 'B'))
        assertThrows(IllegalArgumentException::class.java) {
            testPlugboard.addLink('A', 'A')
        }
    }

    @Test
    fun addLinkNonExistentCharacterThrowIllegalArgumentException() {
        val testPlugboard = Plugboard(charArrayOf('A', 'B'))
        assertThrows(IllegalArgumentException::class.java) {
            testPlugboard.addLink('A', 'C')
        }
    }

    @Test
    fun addLinkCharacterAlreadyWiredThrowIllegalArgumentException() {
        val testPlugboard = Plugboard(charArrayOf('A', 'B'))
        testPlugboard.addLink('A', 'B')
        assertThrows(IllegalArgumentException::class.java) {
            testPlugboard.addLink('A', 'B')
        }
    }
}
