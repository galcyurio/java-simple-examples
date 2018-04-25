import retrofit2.Call
import retrofit2.http.GET

/**
 * @author galcyurio
 */
interface GitHubRequest {

    @GET("/users")
    fun users(): Call<String>
}