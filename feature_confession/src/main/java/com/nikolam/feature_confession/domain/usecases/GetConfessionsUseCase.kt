package com.nikolam.feature_confession.domain.usecases

import com.nikolam.feature_confession.domain.ConfessionRepository
import com.nikolam.feature_confession.domain.models.ConfessionDomainModel
import timber.log.Timber

class GetConfessionsUseCase(
    private val confessionRepository: ConfessionRepository
) {

    sealed class Result {
        data class Success(val confession: ConfessionDomainModel) : Result()
        data class Error(val e: Throwable?) : Result()
    }

    suspend fun execute(id: String): Result {
        try {
            val response = confessionRepository.getConfession(id)
            Timber.d("response is $response")
            if (response.id == "") {
                return Result.Error(null)
            }
            return Result.Success(response)

        } catch (e: Exception) {
            Timber.e("exception thrown ${e.localizedMessage}")
            return Result.Error(e)
        }
    }
}