package com.nikolam.feature_comment.data

import com.nikolam.common.BaseCommentsAPIUrl
import retrofit2.Call
import retrofit2.http.*

interface AddCommentService {
    @POST("$BaseCommentsAPIUrl/")
    fun postComment(@Body text : CommentBody, @Query("id") id : String) : Call<Void>
}