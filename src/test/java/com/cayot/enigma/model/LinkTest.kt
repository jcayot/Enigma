package com.cayot.enigma.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LinkTest {
    @Test
    fun testLinkInitialization() {
        val c1 = 'a'
        val c2 = 'b'
        val link = Link(c1, c2)

        assertEquals(c1, link.getC1())
        assertEquals(c2, link.getC2())
    }

    @Test
    fun testLinkEquality() {
        val link1 = Link('x', 'y')
        val link2 = Link('x', 'y')

        assertEquals(link1.getC1(), link2.getC1())
        assertEquals(link1.getC2(), link2.getC2())
    }
}