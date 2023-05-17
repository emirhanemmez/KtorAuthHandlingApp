package com.eemmez.ktorauthhandlingapp.remote

import com.eemmez.ktorauthhandlingapp.local.LocalService
import com.eemmez.ktorauthhandlingapp.model.Token
import com.eemmez.ktorauthhandlingapp.model.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import javax.inject.Inject

class RemoteService @Inject constructor(
    private val httpClient: HttpClient,
    private val localService: LocalService
) {
    suspend fun login(user: User): Token =
        httpClient.post {
            url("login")
            setBody(user)
        }.body()

    suspend fun authHello(): String {
        val bearerToken = localService.getBearerToken()
        return httpClient.get {
            url("authHello")
            headers {
                append(HttpHeaders.Authorization, "Bearer $bearerToken")
            }
        }.body()
    }
}