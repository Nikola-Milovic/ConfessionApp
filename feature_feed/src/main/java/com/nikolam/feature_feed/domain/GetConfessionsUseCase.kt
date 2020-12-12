package com.nikolam.feature_feed.domain

import timber.log.Timber

class GetConfessionsUseCase(
    private val confessionRepository: ConfessionRepository
) {

    sealed class Result {
        data class Success(val confessions: ArrayList<ConfessionDomainModel>) : Result()
        data class Error(val e: Throwable?) : Result()
    }

    suspend fun execute(sortBy: String): Result {
        try {
            val response = confessionRepository.getConfessions(sortBy)
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