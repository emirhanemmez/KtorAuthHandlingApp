package com.eemmez.ktorauthhandlingapp.usecase

import android.util.Log
import com.eemmez.ktorauthhandlingapp.remote.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthHelloUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
) {
    operator fun invoke(): Flow<Unit> = flow {
        emit(remoteRepository.authHello())
    }.catch {
        Log.e("Error", it.message.orEmpty())
    }.flowOn(Dispatchers.IO)
}