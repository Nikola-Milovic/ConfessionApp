package com.nikolam.feature_newconfession.domain

import com.nikolam.feature_newconfession.data.SaveConfessionResponse
import kotlinx.coroutines.flow.Flow

interface ConfessionRepository {
    suspend fun saveConfession(text: String): SaveConfessionResponse
}