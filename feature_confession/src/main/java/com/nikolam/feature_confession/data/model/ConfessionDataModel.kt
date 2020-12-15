package com.nikolam.feature_confession.data.model

import com.google.gson.annotations.SerializedName
import com.nikolam.feature_confession.domain.models.ConfessionDomainModel

data class ConfessionDataModel(
        @SerializedName("comments") val comments: Int,
        @SerializedName("_id") val _id: String,
        @SerializedName("text") val text: String,
        @SerializedName("date") val date: String,
        @SerializedName("__v") val __v: Int
)


fun ConfessionDataModel.toDomainModel(): ConfessionDomainModel {
    return ConfessionDomainModel(
            comments = comments,
            id = _id,
            text = text,
            date = ""
    )
}