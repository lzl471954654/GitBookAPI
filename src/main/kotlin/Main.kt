import okhttp3.Response
import retrofit2.Call
import java.net.URL
import java.nio.charset.Charset
import java.security.SecureRandom
import java.util.*

val username = "username"
val password = "password"

fun main(args: Array<String>) {
    val authenticationData =getAuthentication(username,password)
    println(authenticationData)
    val api = retrofitClient.create(GitBookApi::class.java)
    val call = api.getSingleAuthorDetails("testUser")
    val res = call.execute()
    println("code is :${res.code()}\nbody is :\n${res.body()?.string()}")
}

fun getAuthentication(username:String,password:String):String{
    return " Basic " + Base64.getEncoder().encodeToString("$username:$password".toByteArray(Charset.forName("UTF-8")));
}