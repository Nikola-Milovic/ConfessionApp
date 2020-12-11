package com.nikolam.feature_feed.data

import com.nikolam.common.BaseAPIUrl
import com.nikolam.feature_feed.data.model.ConfessionDataModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ConfessionFeedService {
    @GET("$BaseAPIUrl/")
    fun getConfessions(): Call<ArrayList<ConfessionDataModel>>
}