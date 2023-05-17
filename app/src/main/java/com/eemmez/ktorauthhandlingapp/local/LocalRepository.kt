package com.eemmez.ktorauthhandlingapp.local

interface LocalRepository {
    suspend fun saveBearerToken(bearerToken: String, expirationTimeInMillis: Long)
    suspend fun saveRefreshToken(refreshToken: String)
    suspend fun getBearerToken(): String?
    suspend fun getRefreshToken(): String?
}