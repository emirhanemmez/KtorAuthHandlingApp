package com.eemmez.ktorauthhandlingapp.local

import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val localService: LocalService
): LocalRepository {
    override suspend fun saveBearerToken(bearerToken: String, expirationTimeInMillis: Long) {
        localService.saveBearerToken(bearerToken)
    }

    override suspend fun saveRefreshToken(refreshToken: String) {
        localService.saveRefreshToken(refreshToken)
    }

    override suspend fun getBearerToken(): String? {
        return localService.getBearerToken()
    }

    override suspend fun getRefreshToken(): String? {
        return localService.getRefreshToken()
    }
}