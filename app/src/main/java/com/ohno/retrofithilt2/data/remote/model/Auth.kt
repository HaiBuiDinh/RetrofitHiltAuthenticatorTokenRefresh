package com.ohno.retrofithilt2.data.remote.model

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("email_address")
    val email: String,
    val password: String
)
