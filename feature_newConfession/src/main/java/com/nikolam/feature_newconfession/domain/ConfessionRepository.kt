package com.nikolam.feature_newconfession.domain

interface ConfessionRepository {
    suspend fun saveConfession(text : String) : String?
}