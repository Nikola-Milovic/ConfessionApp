package com.nikolam.feature_feed.domain

interface ConfessionRepository {
    suspend fun getConfessions(): ArrayList<ConfessionDomainModel>
}