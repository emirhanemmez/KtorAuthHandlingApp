package com.eemmez.ktorauthhandlingapp.usecase

import android.util.Log
import com.eemmez.ktorauthhandlingapp.local.LocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SaveRefreshTokenUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    operator fun invoke(refreshToken: String): Flow<Unit> = flow {
        emit(localRepository.saveRefreshToken(refreshToken))
    }.catch {
        Log.e("Error", it.message.orEmpty())
    }.flowOn(Dispatchers.IO)
}