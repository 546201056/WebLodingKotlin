package com.example.weblodingkotlin.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


abstract class BaseViewModel : ViewModel() {
     /** 请求异常（服务器请求失败，譬如：服务器连接超时等） */
     val exception = MutableLiveData<Exception>()

     /** 请求服务器返回错误（服务器请求成功但status错误，譬如：登录过期等） */
     val errorResponse = MutableLiveData<ApiResponse<*>?>()

}