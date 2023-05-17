package com.eemmez.ktorauthhandlingapp.remote

import com.eemmez.ktorauthhandlingapp.model.Token
import com.eemmez.ktorauthhandlingapp.model.User
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteService: RemoteService
) : RemoteRepository {
    override suspend fun login(user: User): Token {
        return remoteService.login(user)
    }

    override suspend fun authHello() {
        remoteService.authHello()
    }
}