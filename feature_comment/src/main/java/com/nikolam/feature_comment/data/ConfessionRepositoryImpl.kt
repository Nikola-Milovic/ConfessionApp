package com.nikolam.feature_comment.data

import com.nikolam.feature_comment.domain.ConfessionRepository
import com.nikolam.feature_comment.domain.ResponseCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ConfessionRepositoryImpl (private val addCommentService: AddCommentService) :
    ConfessionRepository {

    override suspend fun postComment(text: String, id: String): ResponseCode  = suspendCoroutine { cont ->
        val call = addCommentService.postComment(id = id, text = CommentBody(text))
        Timber.i("$text and id is $id")
        call.enqueue(object : Callback<Void> {
            override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>
            ) {
                cont.resume(response.code())
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                cont.resumeWithException(t)
            }

        })
    }
}