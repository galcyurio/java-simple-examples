package com.github.galcyurio

import org.assertj.core.api.Assertions.assertThat
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
    fun `getMessage 통합 테스트`() {
        val actual = messageService.getMessage()
        assertThat(actual).isEqualTo("Hello, world!")
    }
}