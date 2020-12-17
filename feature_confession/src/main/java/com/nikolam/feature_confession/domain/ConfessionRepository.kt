package com.nikolam.feature_confession.domain

import com.nikolam.feature_confession.domain.models.CommentDomainModel
import com.nikolam.feature_confession.domain.models.ConfessionDomainModel

typealias ResponseCode = Int

interface ConfessionRepository {
    suspend fun getConfession(id : String): ConfessionDomainModel
    suspend fun getComments(id : String): ArrayList<CommentDomainModel>
}