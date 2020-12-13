package com.nikolam.feature_confession.data

import com.nikolam.feature_confession.data.model.ConfessionDataModel
import com.nikolam.feature_confession.data.model.toDomainModel
import com.nikolam.feature_confession.domain.ConfessionDomainModel
import com.nikolam.feature_confession.domain.ConfessionRepository
import com.nikolam.feature_feed.data.ConfessionDetailService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ConfessionRepositoryImpl (private val confessionDetailService: ConfessionDetailService) :
    ConfessionRepository {

    @ExperimentalCoroutinesApi
    override suspend fun getConfession(id : String): ConfessionDomainModel = suspendCoroutine { cont ->
            val call = confessionDetailService.getConfession(id)
            Timber.i("Call enqueued")
            call.enqueue(object : Callback<ConfessionDataModel> {
                override fun onResponse(
                        call: Call<ConfessionDataModel>,
                        response: Response<ConfessionDataModel>
                ) {
                    if(response.isSuccessful){
                        cont.resume(response.body()!!.toDomainModel())
                    } else {
                        cont.resume(ConfessionDomainModel(0,0,"","",""))
                    }
                }

                override fun onFailure(call: Call<ConfessionDataModel>, t: Throwable) {
                   cont.resumeWithException(t)
                }

            })
        }
}