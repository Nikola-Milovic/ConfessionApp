package com.nikolam.feature_confession.data

import com.nikolam.common.BaseCommentsAPIUrl
import com.nikolam.common.BaseConfessionAPIUrl
import com.nikolam.feature_confession.data.model.CommentBody
import com.nikolam.feature_confession.data.model.CommentDataModel
import com.nikolam.feature_confession.data.model.ConfessionDataModel
import retrofit2.Call
import retrofit2.http.*

interface ConfessionDetailService {
    @GET("$BaseConfessionAPIUrl/conf/")
    fun getConfession(@Query("id") id : String): Call<ConfessionDataModel>

    @GET("$BaseCommentsAPIUrl/")
    fun getComments(@Query("id") id : String): Call<ArrayList<CommentDataModel>>

    @POST("$BaseCommentsAPIUrl/")
    fun postComment(@Body text : CommentBody, @Query("id") id : String) : Call<Void>
}