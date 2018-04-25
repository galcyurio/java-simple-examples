import okhttp3.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import java.io.IOException
import java.util.*

/**
 * @author galcyurio
 */
class Tester {

    @Test
    fun `Hello, world!`() {
        // Create a MockWebServer. These are lean enough that you can create a new
        // instance for every unit test.
        val server = MockWebServer()

        // Schedule some responses.
        server.enqueue(MockResponse().setBody("hello, world!"))
        server.enqueue(MockResponse().setBody("sup, bra?"))
        server.enqueue(MockResponse().setBody("yo dog"))

        // Start the server.
        server.start()

        // Ask the server for its URL. You'll need this to make HTTP requests.
        val baseUrl = server.url("/v1/chat/")

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(baseUrl)
            .build()

        val callback: Callback = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                println(response.body()!!.string())
            }
        }
        client.newCall(request).enqueue(callback)
        client.newCall(request).enqueue(callback)
        client.newCall(request).enqueue(callback)
        client.newCall(request).enqueue(callback)

        Thread.sleep(1000)

        // Shut down the server. Instances cannot be reused.
        server.shutdown()
    }

    @Test
    fun `복잡한 MockResponse 예제`() {
        val server = MockWebServer()

        server.enqueue(MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .addHeader("Cache-Control", "no-cache")
            .setBody("""
                {
                  "id": ${UUID.randomUUID()},
                  "name": "dummy"
                }
            """.trimIndent()))

        val response = OkHttpClient()
            .newCall(Request.Builder().url(server.url("/people")).build())
            .execute()
        println("--headers \n${response.headers()}")
        println("--body \n${response.body()!!.string()}")
    }

}