package com.example.shifr.retrofit

import com.example.shifr.Constants
import com.example.shifr.globa
import com.example.shifr.retrofit.Model.MainResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {
    @GET("{postNumber}")
    suspend fun getPost(
        @Path("postNumber") endPoint:String
    ):Response<MainResponse>
}