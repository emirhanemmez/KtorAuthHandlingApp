package com.eemmez.ktorauthhandlingapp.remote

import com.eemmez.ktorauthhandlingapp.model.Token
import com.eemmez.ktorauthhandlingapp.model.User

interface RemoteRepository {
    suspend fun login(user: User): Token
    suspend fun authHello()
}