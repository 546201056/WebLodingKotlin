package com.example.weblodingkotlin.http

import com.example.weblodingkotlin.base.viewmodel.ApiResponse
import com.example.weblodingkotlin.model.Banner
import com.example.weblodingkotlin.model.DemoModel
import com.example.weblodingkotlin.model.RecordModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface IApiService {

    companion object {
        val baseUrl = "https://www.wanandroid.com"
    }

    @GET("api")
    fun apiDemo(): Call<DemoModel>

    @POST("/account/login")
    suspend fun login():RecordModel

    /** 获取首页banner数据 */
    @GET("banner/json")
    suspend  fun getBanner(): ApiResponse<List<Banner>>

}