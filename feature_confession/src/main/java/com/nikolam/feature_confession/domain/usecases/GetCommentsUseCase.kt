package com.nikolam.feature_confession.domain.usecases

import com.nikolam.feature_confession.domain.ConfessionRepository
import com.nikolam.feature_confession.domain.models.CommentDomainModel
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
            return Result.Success(response)

        } catch (e: Exception) {
            Timber.e("exception thrown ${e.localizedMessage}")
            return Result.Error(e)
        }
    }
}