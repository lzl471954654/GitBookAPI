import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.*
import java.util.concurrent.TimeUnit

val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(15,TimeUnit.SECONDS)
        .build()
val retrofitClient = Retrofit.Builder()
        .baseUrl("https://api.gitbook.com")
        .client(okHttpClient)
        .build()


public interface GitBookApi{

    // Base infomation API
    @GET("/books")
    fun listYourBook(@Header("Authorization") authorization : String, @Query("page") pageNumber : Int = 0):Call<ResponseBody>

    @GET("/books/all")
    fun listPublicBooks(@Query("page") pageNumber: Int = 0):Call<ResponseBody>

    @GET("/book/{username}/{bookName}")
    fun getBookDetails(@Path("username") username : String, @Path("bookName") bookName : String):Call<ResponseBody>

    @GET("/authors/{username}/books")
    fun listAuthorBooks(@Path("username") username: String, @Query("page") pageNumber: Int = 0)


    // Traffic API
    @GET("/book/{username}/{bookName}/traffic")
    fun getBookTrafficInfo(@Path("username") username: String, @Path("bookName") bookName: String, @Header("Authorization") authorization: String):Call<ResponseBody>

    @GET("/book/{username}/{bookName}/traffic/countries")
    fun getVisitPerCountries(@Path("username") username: String, @Path("bookName") bookName: String, @Header("Authorization") authorization: String):Call<ResponseBody>

    @GET("/book/{username}/{bookName}/traffic/platforms")
    fun getVisitPerPlatforms(@Path("username") username: String, @Path("bookName") bookName: String, @Header("Authorization") authorization: String):Call<ResponseBody>


    //Version API
    @GET("/book/{username}/{bookName}/versions/branches")
    fun listBookBranches(@Path("username") username: String, @Path("bookName") bookName: String): Call<ResponseBody>

    @GET("/book/{username}/{bookName}/versions/tags")
    fun listBookTags(@Path("username") username: String, @Path("bookName") bookName: String):Call<ResponseBody>

    @GET("/book/{username}/{bookName}/versions/languages")
    fun listBookLanguages(@Path("username") username: String, @Path("bookName") bookName: String):Call<ResponseBody>


    //Content API

    @GET("/book/@{username}/@{bookName}/contents/README.json")
    fun getReadMeByNewVersionAPI(@Path("username") username: String, @Path("bookName") bookName: String, @Header("Accept") accept : String = "Accept: application/json;application/vnd.gitbook.format.v3"):Call<ResponseBody>

    @GET("/book/@{username}/@{bookName}/contents/@{file}")
    fun getContents(@Path("username") username: String, @Path("bookName") bookName: String, @Path("file") file : String):Call<ResponseBody>


    // Authors API

    @GET("/authors/@{username}")
    fun getSingleAuthorDetails(@Path("username") username: String):Call<ResponseBody>

    @GET("/authors/@{username}/orgs")
    fun getUserOrganizations(@Path("username") username: String):Call<ResponseBody>

    // Topic API

    @GET("/topics")
    fun listTopics(@Query("page") pageNumber: Int = 0, @Query("q") queryName : String = ""):Call<ResponseBody>

    @GET("/topic/@{topicID}")
    fun getSpecificTopic(@Path("topicID") topicID : String):Call<ResponseBody>
}