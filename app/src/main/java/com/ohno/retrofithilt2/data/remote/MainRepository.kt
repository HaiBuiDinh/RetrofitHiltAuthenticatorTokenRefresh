package com.ohno.retrofithilt2.data.remote

import com.ohno.retrofithilt2.data.remote.retrofit.MainApiService
import com.ohno.retrofithilt2.util.apiRequestFlow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mainApiService: MainApiService
) {
    fun getUserInfo() = apiRequestFlow {
        mainApiService.getUserInfo()
    }
}