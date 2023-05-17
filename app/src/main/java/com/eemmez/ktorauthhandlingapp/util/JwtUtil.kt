package com.eemmez.ktorauthhandlingapp.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import java.util.Date

object JwtUtil {
    private const val secretKey = "secret"
    private const val tokenClaim = "issuer"
    private val algorithm = Algorithm.HMAC256(secretKey)

    fun buildToken(token: String, expiration: Long): String {
        val now = System.currentTimeMillis()
        val nowDate = Date(now)
        val expirationDate = Date(now + expiration * 1000)
        return JWT.create()
            .withClaim(tokenClaim, token)
            .withIssuedAt(nowDate)
            .withExpiresAt(expirationDate)
            .sign(algorithm)
    }

    fun isTokenExpired(encodedToken: String): Boolean {
        return try {
            val verifier = JWT.require(algorithm).build()
            verifier.verify(encodedToken)
            false
        } catch (e: JWTVerificationException) {
            true
        }
    }

    fun decodeToken(encodedToken: String?): String? {
        return try {
            val verifier = JWT.require(algorithm).build()
            verifier.verify(encodedToken).getClaim(tokenClaim).asString()
        } catch (e: JWTVerificationException) {
            null
        }
    }
}