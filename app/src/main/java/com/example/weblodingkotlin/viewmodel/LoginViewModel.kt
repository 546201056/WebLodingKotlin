package com.example.weblodingkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weblodingkotlin.base.viewmodel.ApiResponse
import com.example.weblodingkotlin.base.viewmodel.BaseViewModel
import com.example.weblodingkotlin.http.IApiService
import com.example.weblodingkotlin.http.RetrofitUtil
import com.example.weblodingkotlin.model.Banner
import com.example.weblodingkotlin.model.RecordModel
import com.example.weblodingkotlin.repository.APIRepository
import kotlinx.coroutines.launch


class LoginViewModel() : BaseViewModel() {

    var loginLiveData = MutableLiveData<RecordModel>()

    private val api = RetrofitUtil.getService(IApiService::class.java)

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            loginLiveData.value =   APIRepository().login(userName)
        }
    }
}
