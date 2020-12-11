package com.nikolam.feature_feed.data

import com.nikolam.feature_feed.data.model.ConfessionDataModel
import com.nikolam.feature_feed.data.model.toDomainModel
import com.nikolam.feature_feed.domain.ConfessionDomainModel
import com.nikolam.feature_feed.domain.ConfessionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ConfessionRepositoryImpl (private val confessionFeedService: ConfessionFeedService) :
    ConfessionRepository {

    @ExperimentalCoroutinesApi
    override suspend fun getConfessions(): ArrayList<ConfessionDomainModel> =
        suspendCoroutine { cont ->
            val call = confessionFeedService.getConfessions()
            Timber.i("Call enqueued")
            call.enqueue(object : Callback<ArrayList<ConfessionDataModel>> {
                override fun onResponse(
                    call: Call<ArrayList<ConfessionDataModel>>,
                    response: Response<ArrayList<ConfessionDataModel>>
                ) {
                    if(response.isSuccessful){
                        val res = response.body()!!.map { it.toDomainModel() }
                        cont.resume(ArrayList(res))
                    } else {
                        cont.resume(arrayListOf())
                    }
                }

                override fun onFailure(call: Call<ArrayList<ConfessionDataModel>>, t: Throwable) {
                   cont.resumeWithException(t)
                }

            })
        }
}