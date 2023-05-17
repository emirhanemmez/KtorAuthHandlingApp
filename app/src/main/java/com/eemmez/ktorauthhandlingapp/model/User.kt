package com.eemmez.ktorauthhandlingapp.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val password: String
)
