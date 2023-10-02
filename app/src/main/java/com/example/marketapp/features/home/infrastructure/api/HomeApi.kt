package com.example.marketapp.features.home.infrastructure.api

import com.example.marketapp.features.auth.infrastructure.api.request.*
import com.example.marketapp.features.home.data.entities.home.HomeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface HomeApi {

    @Headers("Content-Type: application/json")
    @GET("api/home")
    suspend fun home(
        @Header("Authorization") token : String
    ): Response<HomeResponse>

}