package com.eemmez.ktorauthhandlingapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eemmez.ktorauthhandlingapp.model.User
import com.eemmez.ktorauthhandlingapp.usecase.AuthHelloUseCase
import com.eemmez.ktorauthhandlingapp.usecase.LoginUseCase
import com.eemmez.ktorauthhandlingapp.usecase.SaveBearerTokenUseCase
import com.eemmez.ktorauthhandlingapp.usecase.SaveRefreshTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveBearerTokenUseCase: SaveBearerTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
    private val authHelloUseCase: AuthHelloUseCase
) : ViewModel() {

    fun login(user: User) {
        viewModelScope.launch {
            loginUseCase.invoke(user)
                .collect {
                    Log.e("Token", it.toString())
                    saveBearerTokenUseCase.invoke(it.bearerToken, it.expirationTimeInMillis).collect()
                    saveRefreshTokenUseCase.invoke(it.refreshToken).collect()
                }
        }
    }

    fun authHello() {
        viewModelScope.launch {
            authHelloUseCase.invoke().collect()
        }
    }
}