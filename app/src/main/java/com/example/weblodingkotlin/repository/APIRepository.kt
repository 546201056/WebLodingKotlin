package com.example.weblodingkotlin.repository

import com.example.weblodingkotlin.base.viewmodel.ApiResponse
import com.example.weblodingkotlin.http.IApiService
import com.example.weblodingkotlin.http.RetrofitUtil
import com.example.weblodingkotlin.model.Banner
import com.example.weblodingkotlin.model.RecordModel

class APIRepository {

    suspend fun login(name:String): RecordModel {
        return RetrofitUtil.getService(IApiService::class.java).login()
    }
}