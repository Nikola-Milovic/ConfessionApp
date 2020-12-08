package com.nikolam.feature_newconfession.domain.usecases

import com.nikolam.feature_newconfession.domain.ConfessionRepository

internal class SaveConfessionUseCase(
        private val confessionRepository: ConfessionRepository
) {

    sealed class Result {
        data class Success(val data: String) : Result()
        data class Error(val e: Throwable?) : Result()
    }

    suspend fun execute(text: String): Result {
        val id = confessionRepository.saveConfession(text)
        if (id.isNullOrEmpty()) {
            return Result.Error(null)
        } else {
            return Result.Success(id)
        }
    }

}

