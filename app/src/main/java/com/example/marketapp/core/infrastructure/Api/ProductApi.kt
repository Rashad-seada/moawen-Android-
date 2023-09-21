package com.example.marketapp.core.infrastructure.Api

import retrofit2.http.GET

interface ProductApi {
    @GET("/products")
    suspend fun getProducts() : ProductsResponse

//    @GET("/products/{id}")
//    suspend fun getProducts(@Query("id") id : Int) : ProductsResponse

}