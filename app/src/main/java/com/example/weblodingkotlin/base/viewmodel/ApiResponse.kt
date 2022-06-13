package com.example.weblodingkotlin.base.viewmodel


data class ApiResponse<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)