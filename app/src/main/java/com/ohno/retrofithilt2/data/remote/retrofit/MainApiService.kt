package com.ohno.retrofithilt2.data.remote.retrofit

import com.ohno.retrofithilt2.data.remote.model.UserInfoResponse
import retrofit2.Response
import retrofit2.http.GET

interface MainApiService {
    @GET("user/info")
    suspend fun getUserInfo(): Response<UserInfoResponse>
}