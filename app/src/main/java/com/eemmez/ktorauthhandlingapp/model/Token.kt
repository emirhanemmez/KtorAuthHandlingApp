package com.eemmez.ktorauthhandlingapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val bearerToken: String,
    val refreshToken: String,
    val expirationTimeInMillis: Long
)
