package com.eemmez.ktorauthhandlingapp.usecase

import android.util.Log
import com.eemmez.ktorauthhandlingapp.local.LocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SaveBearerTokenUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    operator fun invoke(bearerToken: String, expirationTimeInMillis: Long): Flow<Unit> = flow {
        emit(localRepository.saveBearerToken(bearerToken, expirationTimeInMillis))
    }.catch {
        Log.e("Error", it.message.orEmpty())
    }.flowOn(Dispatchers.IO)
}