package com.nikolam.feature_confession.domain

data class ConfessionDomainModel(
    val likes: Int,
    val dislikes: Int,
    val id: String,
    val text: String,
    val date: String,
)
