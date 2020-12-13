package com.nikolam.feature_confession.domain

import com.nikolam.feature_confession.domain.ConfessionDomainModel

interface ConfessionRepository {
    suspend fun getConfession(id : String): ConfessionDomainModel
}