package com.github.galcyurio

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author galcyurio
 */
class MessageServiceTest {

    lateinit var messageService: MessageService

    @Before
    fun setUp() {
        messageService = MessageService()
    }

    @Test
    fun `getMessage 단위 테스트`() {
        val actual = messageService.getMessage()
        assertEquals("Hello, world!", actual)
    }
}