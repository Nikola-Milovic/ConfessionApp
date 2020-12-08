package com.nikolam.feature_newconfession.data

import com.nikolam.feature_newconfession.data.NewConfessionService
import com.nikolam.feature_newconfession.domain.ConfessionRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class ConfessionRepositoryImpl(private val newConfessionService: NewConfessionService) : ConfessionRepository{

    override suspend fun saveConfession(text: String): String? {
        val confessionToSave = ConfessionDataModel(text)
        val call = newConfessionService.saveConfession(confessionToSave)
        var id = ""
        call.enqueue(object : Callback<SaveConfessionResponse>{
            override fun onResponse(call: Call<SaveConfessionResponse>, response: Response<SaveConfessionResponse>) {
                if(response.isSuccessful){
                    if(response.body()!!.status != 500){
                        id = response.body()!!.id
                    }
                }
            }

            override fun onFailure(call: Call<SaveConfessionResponse>, t: Throwable) {
                Timber.e(t)
            }

        })

        return if (id.isBlank()) null else id
    }

}