package com.nikolam.feature_feed.data.model

import android.icu.text.SimpleDateFormat
import com.google.gson.annotations.SerializedName
import com.nikolam.feature_feed.domain.ConfessionDomainModel
import java.time.format.DateTimeFormatter


data class ConfessionDataModel(
        @SerializedName("likes") val likes: Int,
        @SerializedName("dislikes") val dislikes: Int,
        @SerializedName("_id") val _id: String,
        @SerializedName("text") val text: String,
        @SerializedName("date") val date: String,
        @SerializedName("__v") val __v: Int
)

fun ConfessionDataModel.toDomainModel() : ConfessionDomainModel{
//    val parsedDate = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//        SimpleDateFormat.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
//    } else {
//        TODO("VERSION.SDK_INT < O")
//    }

    return ConfessionDomainModel(
            likes = likes,
            dislikes = dislikes,
            id = _id,
            text = text,
            date = ""
    )
}