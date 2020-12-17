package com.nikolam.feature_comment.domain

typealias ResponseCode = Int

interface ConfessionRepository {
    suspend fun postComment(text : String, id : String) : ResponseCode
}