package com.nikolam.feature_newconfession.domain.usecases

import com.nikolam.feature_newconfession.data.SaveConfessionResponse
import com.nikolam.feature_newconfession.domain.ConfessionRepository
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.suspendCoroutine

internal class SaveConfessionUseCase(
    private val confessionRepository: ConfessionRepository
) {

    sealed class Result {
        data class Success(val id: String, val status: Int) : Result()
        data class Error(val e: Throwable?) : Result()
    }

    suspend fun execute(text: String): Result {
        try {
            val response = confessionRepository.saveConfession(text)
            Timber.d("response is ${response.status}")
            return when (response.status) {
                200 -> Result.Success(response.id, 200)
                400 -> Result.Error(null)
                500 -> Result.Error(null)
                else -> Result.Error(null)
            }
        } catch (e: Exception) {
            Timber.e("exception thrown ${e.localizedMessage}")
            return Result.Error(e)

        }
    }

}

