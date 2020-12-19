package com.nikolam.feature_confession.data

import android.content.Context
import com.nikolam.feature_confession.data.model.CommentDataModel
import com.nikolam.feature_confession.data.model.ConfessionDataModel
import com.nikolam.feature_confession.data.model.toDomainModel
import com.nikolam.feature_confession.domain.ConfessionRepository
import com.nikolam.feature_confession.domain.models.CommentDomainModel
import com.nikolam.feature_confession.domain.models.ConfessionDomainModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ConfessionRepositoryImpl(
    private val confessionDetailService: ConfessionDetailService,
    private val context: Context
) :
    ConfessionRepository {

    @ExperimentalCoroutinesApi
    override suspend fun getConfession(id: String): ConfessionDomainModel =
        suspendCoroutine { cont ->
            val call = confessionDetailService.getConfession(id)
            call.enqueue(object : Callback<ConfessionDataModel> {
                override fun onResponse(
                    call: Call<ConfessionDataModel>,
                    response: Response<ConfessionDataModel>
                ) {
                    if (response.isSuccessful) {
                        cont.resume(response.body()!!.toDomainModel(context))
                    } else {
                        cont.resume(ConfessionDomainModel(0, "", "", ""))
                    }
                }

                override fun onFailure(call: Call<ConfessionDataModel>, t: Throwable) {
                    cont.resumeWithException(t)
                }

            })
        }

    override suspend fun getComments(id: String): ArrayList<CommentDomainModel> =
        suspendCoroutine { cont ->
            val call = confessionDetailService.getComments(id)
            call.enqueue(object : Callback<ArrayList<CommentDataModel>> {
                override fun onResponse(
                    call: Call<ArrayList<CommentDataModel>>,
                    response: Response<ArrayList<CommentDataModel>>
                ) {
                    if (response.isSuccessful) {
                        val res = response.body()!!.map { it.toDomainModel(context) }
                        cont.resume(ArrayList(res))
                    } else {
                        cont.resume(arrayListOf())
                    }
                }

                override fun onFailure(call: Call<ArrayList<CommentDataModel>>, t: Throwable) {
                    cont.resumeWithException(t)
                }

            })
        }
}