package com.ohno.retrofithilt2.ui.fragment

import androidx.lifecycle.MutableLiveData
import com.ohno.retrofithilt2.data.remote.MainRepository
import com.ohno.retrofithilt2.data.remote.model.UserInfoResponse
import com.ohno.retrofithilt2.ui.BaseViewModel
import com.ohno.retrofithilt2.ui.CoroutinesErrorHandler
import com.ohno.retrofithilt2.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): BaseViewModel() {

    private val _userInfoResponse = MutableLiveData<ApiResponse<UserInfoResponse>>()
    val userInfoResponse = _userInfoResponse

    fun getUserInfo(coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
        _userInfoResponse,
        coroutinesErrorHandler
    ) {
        mainRepository.getUserInfo()
    }

}