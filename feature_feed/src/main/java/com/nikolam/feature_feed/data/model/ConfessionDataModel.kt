package com.nikolam.feature_feed.data.model

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import com.nikolam.common.utils.GetTimePassed
import com.nikolam.feature_feed.R
import com.nikolam.feature_feed.domain.ConfessionDomainModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


data class ConfessionDataModel(
        @SerializedName("comments") val comments: Int,
        @SerializedName("_id") val _id: String,
        @SerializedName("text") val text: String,
        @SerializedName("date") val date: String,
        @SerializedName("__v") val __v: Int
)


fun ConfessionDataModel.toDomainModel(context : Context): ConfessionDomainModel {

    var time: OffsetDateTime = OffsetDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)

    var timePassed = GetTimePassed.getTimeAgo(time.toEpochSecond() * 1000, context)

    if(timePassed == null){
        timePassed = "Moments ago"
    }

    return ConfessionDomainModel(
            comments = comments,
            id = _id,
            text = text,
            date = timePassed
    )
}