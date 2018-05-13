import com.github.galcyurio.MessageService
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author galcyurio
 */
class MessageServiceTest {

    @Test
    fun `단위 테스트`() {
        val service = MessageService()
        val actual = service.getMessage()
        assertEquals("msg", actual)
    }
}