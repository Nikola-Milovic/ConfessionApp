package com.nikolam.feature_newconfession.data

import com.nikolam.common.BaseConfessionAPIUrl
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NewConfessionService {
    @POST("$BaseConfessionAPIUrl/")
    fun saveConfession(@Body text : ConfessionDataModel): Call<SaveConfessionResponse>
}