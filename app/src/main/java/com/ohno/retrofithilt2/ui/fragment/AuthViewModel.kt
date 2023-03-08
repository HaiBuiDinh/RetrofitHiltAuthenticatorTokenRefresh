package com.ohno.retrofithilt2.ui.fragment

import androidx.lifecycle.MutableLiveData
import com.ohno.retrofithilt2.data.remote.AuthRepository
import com.ohno.retrofithilt2.data.remote.model.Auth
import com.ohno.retrofithilt2.data.remote.model.LoginResponse
import com.ohno.retrofithilt2.ui.BaseViewModel
import com.ohno.retrofithilt2.ui.CoroutinesErrorHandler
import com.ohno.retrofithilt2.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel() {
    private val _loginResponse = MutableLiveData<ApiResponse<LoginResponse>>()
    val loginResponse = _loginResponse

    fun login(auth: Auth, coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
        _loginResponse,
        coroutinesErrorHandler
    ) {
        authRepository.login(auth)
    }
}