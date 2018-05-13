package com.github.galcyurio

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * @author galcyurio
 */
class MessageServiceTest {

    @Test
    fun `통합 테스트`() {
        val service = MessageService()
        val actual = service.getMessage()
        assertThat(actual).isEqualTo("msg")
    }
}