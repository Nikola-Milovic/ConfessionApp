package com.nikolam.feature_feed.data.model

import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import com.nikolam.feature_feed.domain.ConfessionDomainModel
import timber.log.Timber
import java.time.format.DateTimeFormatter
import java.util.*


data class ConfessionDataModel(
        @SerializedName("comments") val comments: Int,
        @SerializedName("_id") val _id: String,
        @SerializedName("text") val text: String,
        @SerializedName("date") val date: String,
        @SerializedName("__v") val __v: Int
)

@RequiresApi(Build.VERSION_CODES.O)
fun ConfessionDataModel.toDomainModel(): ConfessionDomainModel {
//    val parsedDate = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      // SimpleDateFormat.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
//    } else {
//        TODO("VERSION.SDK_INT < O")
//    }

//    DateUtils.getRelativeTimeSpanString(due, Date(), 0L, DateUtils.FORMAT_ABBREV_ALL);
//    val formatter = DateTimeFormatter.ofPattern(DateTimeFormatter.ISO_OFFSET_DATE_TIME.toString())
//    val output: CharSequence = DateUtils.getRelativeTimeSpanString(dt.time)
//
//    Timber.d("$past")
   /// Date now = new Date();


    return ConfessionDomainModel(
            comments = comments,
            id = _id,
            text = text,
            date = ""
    )
}