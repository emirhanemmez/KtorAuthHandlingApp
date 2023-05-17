package com.eemmez.ktorauthhandlingapp.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class LocalService @Inject constructor(@ApplicationContext private val context: Context) {
    private val Context.dataStore by preferencesDataStore(context.packageName)

    suspend fun saveBearerToken(bearerToken: String) {
        context.dataStore.edit {
            it[bearerTokenKey] = bearerToken
        }
    }

    suspend fun saveRefreshToken(refreshToken: String) {
        context.dataStore.edit {
            it[refreshTokenKey] = refreshToken
        }
    }

    suspend fun getBearerToken(): String? {
        return context.dataStore.data.firstOrNull()?.get(bearerTokenKey)
    }

    suspend fun getRefreshToken(): String? {
        return context.dataStore.data.firstOrNull()?.get(refreshTokenKey)
    }

    private val bearerTokenKey = stringPreferencesKey("bearer_token")
    private val refreshTokenKey = stringPreferencesKey("refresh_token")
}