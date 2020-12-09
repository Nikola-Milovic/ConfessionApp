package com.nikolam.feature_newconfession.data

import com.nikolam.feature_newconfession.domain.ConfessionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ConfessionRepositoryImpl(private val newConfessionService: NewConfessionService) :
    ConfessionRepository {

    @ExperimentalCoroutinesApi
    override suspend fun saveConfession(text: String): SaveConfessionResponse =
        suspendCoroutine { cont ->
            val confessionToSave = ConfessionDataModel(text)
            val call = newConfessionService.saveConfession(confessionToSave)
            Timber.i("Call enqueued")
            call.enqueue(object : Callback<SaveConfessionResponse> {
                override fun onResponse(
                    call: Call<SaveConfessionResponse>,
                    response: Response<SaveConfessionResponse>
                ) {
                    if(response.isSuccessful){
                        cont.resume(response.body()!!)
                    } else {
                        cont.resume(SaveConfessionResponse("", response.code()))
                    }
                }

                override fun onFailure(call: Call<SaveConfessionResponse>, t: Throwable) {
                    Timber.e(t)
                    cont.resumeWithException(t)
                }
            })
        }

}