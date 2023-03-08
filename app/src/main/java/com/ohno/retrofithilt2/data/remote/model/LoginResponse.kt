package com.ohno.retrofithilt2.data.remote.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val token: String
)
