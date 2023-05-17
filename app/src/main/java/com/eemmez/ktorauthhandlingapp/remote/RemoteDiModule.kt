package com.eemmez.ktorauthhandlingapp.remote

import android.util.Log
import com.eemmez.ktorauthhandlingapp.local.LocalService
import com.eemmez.ktorauthhandlingapp.model.Token
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDiModule {
    @Provides
    @Singleton
    fun provideHttpClient(localService: LocalService): HttpClient =
        HttpClient(Android).config {
            defaultRequest {
                url("http://10.0.2.2:8080/")
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.i("HttpClient", message)
                    }
                }
            }
            install(ContentNegotiation) {
                json()
            }
            install(Auth) {
                bearer {
                    refreshTokens {
                        val token = client.get {
                            markAsRefreshTokenRequest()
                            url("refreshToken")
                            parameter("refreshToken", localService.getRefreshToken())
                        }.body<Token>()
                        BearerTokens(
                            accessToken = token.bearerToken,
                            refreshToken = token.refreshToken
                        )
                    }
                }
            }
        }

    @Provides
    @Singleton
    fun provideRemoteService(httpClient: HttpClient, localService: LocalService): RemoteService =
        RemoteService(httpClient, localService)

    @Provides
    @Singleton
    fun provideRemoteRepository(remoteService: RemoteService): RemoteRepository =
        RemoteRepositoryImpl(remoteService)
}