package com.example.marketapp.features.auth.infrastructure.Api

import com.example.marketapp.features.auth.data.entities.LoginEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AuthApi {

    @Headers("Content-Type: application/json")
    @GET("/SRV")
    suspend fun login(
        @Query("srv_code") serviceCode: Int,
        @Query("fid") folderId: Int,
        @Query("userid") userId: String,
        @Query("uname") username: String,
        @Query("upass") password: String,
        @Query("SRV_DATA") serviceData: String,
    ): Response<LoginEntity>



//    @GET("/products/{id}")
//    suspend fun getProducts(@Query("id") id : Int) : ProductsResponse

}