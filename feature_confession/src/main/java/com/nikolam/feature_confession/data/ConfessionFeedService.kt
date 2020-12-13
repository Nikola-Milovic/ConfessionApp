package com.nikolam.feature_feed.data

import com.nikolam.common.BaseAPIUrl
import com.nikolam.feature_confession.data.model.ConfessionDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ConfessionDetailService {
    @GET("$BaseAPIUrl/conf/")
    fun getConfession(@Query("id") id : String): Call<ConfessionDataModel>
}