package com.ohno.retrofithilt2.data

import com.ohno.retrofithilt2.data.remote.AuthRepository
import com.ohno.retrofithilt2.data.remote.MainRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(
    authRepository: AuthRepository,
    mainRepository: MainRepository
) {

    val mAuthData = authRepository
    val mMainData = mainRepository
}