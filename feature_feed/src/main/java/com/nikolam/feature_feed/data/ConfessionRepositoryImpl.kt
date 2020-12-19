package com.nikolam.feature_feed.data

import android.content.Context
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

class ConfessionRepositoryImpl(
    private val confessionFeedService: ConfessionFeedService,
    private val context: Context
) :
    ConfessionRepository {

    @ExperimentalCoroutinesApi
    override suspend fun getConfessions(sortBy: String): ArrayList<ConfessionDomainModel> =
        suspendCoroutine { cont ->
            Timber.d("sort by in repo is $sortBy")
            val call = confessionFeedService.getConfessions(sortBy)
            Timber.i("Call enqueued")
            call.enqueue(object : Callback<ArrayList<ConfessionDataModel>> {
                override fun onResponse(
                    call: Call<ArrayList<ConfessionDataModel>>,
                    response: Response<ArrayList<ConfessionDataModel>>
                ) {
                    if (response.isSuccessful) {
                        val res = response.body()!!.map { it.toDomainModel(context) }
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