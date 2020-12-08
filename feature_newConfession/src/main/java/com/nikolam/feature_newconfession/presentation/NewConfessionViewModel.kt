package com.nikolam.feature_newconfession.presentation

import androidx.lifecycle.ViewModel
import com.nikolam.common.BaseAPIUrl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import timber.log.Timber


class NewConfessionViewModel : ViewModel(), KoinComponent {

    val retrofit : Retrofit by inject()


    init {
        val service: ConfessionService = retrofit.create(ConfessionService::class.java)
        val call = service.createConfession(newConfession("Hello MY BOYS FROM ANDROID APPLICATION"))
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Timber.d("""Response is ${response.body().toString()}""")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Timber.d("Failed fetch with %s", t.localizedMessage)
            }

        })
    }


}

interface ConfessionService {
    @GET("$BaseAPIUrl/")
    fun listRepos(): Call<List<Confession?>?>

    @POST("$BaseAPIUrl/")
    fun createConfession(@Body confession: newConfession): Call<String>
}

data class newConfession(val text: String)

data class Confession(
        val _id: String,
        val text: String,
        val date: String,
        val likes: Int,
        val dislikes: Int,
        val _v: Int
)