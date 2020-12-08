package com.nikolam.feature_newconfession.data

import com.nikolam.common.BaseAPIUrl
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NewConfessionService {
    @POST("$BaseAPIUrl/")
    fun saveConfession(@Body text : ConfessionDataModel): Call<SaveConfessionResponse>
}