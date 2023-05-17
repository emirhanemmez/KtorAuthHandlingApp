package com.eemmez.ktorauthhandlingapp.usecase

import android.util.Log
import com.eemmez.ktorauthhandlingapp.model.Token
import com.eemmez.ktorauthhandlingapp.model.User
import com.eemmez.ktorauthhandlingapp.remote.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
) {
    operator fun invoke(user: User): Flow<Token> = flow {
        emit(remoteRepository.login(user))
    }.catch {
        Log.e("Error", it.message.orEmpty())
    }.flowOn(Dispatchers.IO)
}