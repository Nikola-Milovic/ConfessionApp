package com.nikolam.feature_feed.data

import com.nikolam.common.BaseConfessionAPIUrl
import com.nikolam.feature_feed.data.model.ConfessionDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ConfessionFeedService {
    @GET("$BaseConfessionAPIUrl/")
    fun getConfessions(@Query("sort") sortBy : String): Call<ArrayList<ConfessionDataModel>>
}