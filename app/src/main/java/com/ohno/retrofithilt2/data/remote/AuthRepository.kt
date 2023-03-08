package com.ohno.retrofithilt2.data.remote

import com.ohno.retrofithilt2.data.remote.model.Auth
import com.ohno.retrofithilt2.data.remote.retrofit.AuthApiService
import com.ohno.retrofithilt2.util.apiRequestFlow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApiService: AuthApiService
) {
    fun login(auth: Auth) = apiRequestFlow {
        authApiService.login(auth)
    }
}