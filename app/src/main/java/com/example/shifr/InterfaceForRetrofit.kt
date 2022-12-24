package com.example.shifr

import com.example.shifr.retrofit.Model.MainResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface InterfaceForRetrofit {
    fun getData(endPoint: String): Flow<Response<MainResponse>>
}