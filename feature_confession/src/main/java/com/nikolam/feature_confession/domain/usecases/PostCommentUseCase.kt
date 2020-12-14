package com.nikolam.feature_confession.domain.usecases

import com.nikolam.feature_confession.domain.ConfessionRepository
import timber.log.Timber

class PostCommentUseCase(
        private val confessionRepository: ConfessionRepository
) {

    sealed class Result {
        object Success : Result()
        data class Error(val e: Throwable?) : Result()
    }

    suspend fun execute(text: String, id: String): Result {
        try {
            val response = confessionRepository.postComment(text, id)
            if(response == 200){
                return Result.Success
            } else {
                return Result.Error(null)
            }

        } catch (e: Exception) {
            Timber.e("exception thrown ${e.localizedMessage}")
            return Result.Error(e)
        }
    }
}