package com.nikolam.feature_feed.data.model

import com.google.gson.annotations.SerializedName
import com.nikolam.feature_feed.domain.ConfessionDomainModel
import timber.log.Timber
import java.time.LocalDate

data class ConfessionDataModel (
    @SerializedName("likes") val likes : Int,
    @SerializedName("dislikes") val dislikes : Int,
    @SerializedName("_id") val _id : String,
    @SerializedName("text") val text : String,
    @SerializedName("date") val date : String,
    @SerializedName("__v") val __v : Int
)

fun ConfessionDataModel.toDomainModel() : ConfessionDomainModel{
//    val d = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//        LocalDate.parse("2020-12-07T12:23:58.234+00:00")
//    } else {
//        TODO("VERSION.SDK_INT < O")
//    }
//    Timber.d("Date is $d")

    return ConfessionDomainModel(
        likes = likes,
        dislikes = dislikes,
        id = _id,
        text = text,
        date = ""
    )
}