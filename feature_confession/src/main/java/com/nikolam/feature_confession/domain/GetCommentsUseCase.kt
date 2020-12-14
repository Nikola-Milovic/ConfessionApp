package com.nikolam.feature_confession.domain

import com.nikolam.feature_confession.domain.models.CommentDomainModel
import com.nikolam.feature_confession.domain.models.ConfessionDomainModel
import timber.log.Timber

class GetCommentsUseCase(
    private val confessionRepository: ConfessionRepository
) {

    sealed class Result {
        data class Success(val comments: ArrayList<CommentDomainModel>) : Result()
        data class Error(val e: Throwable?) : Result()
    }

    suspend fun execute(id: String): Result {
        try {
            val response = confessionRepository.getComments(id)
            Timber.d("response is $response")
            if (response.isEmpty()) {
                return Result.Error(null)
            }
            return Result.Success(response)

        } catch (e: Exception) {
            Timber.e("exception thrown ${e.localizedMessage}")
            return Result.Error(e)
        }
    }
}