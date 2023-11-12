package com.example.marketapp.features.order.infrastructure.api

import com.example.marketapp.features.auth.infrastructure.api.request.*
import com.example.marketapp.features.order.data.entities.cancel_order.CancelOrderResponse
import com.example.marketapp.features.order.data.entities.make_order_step_1.MakeOrderStep1Response
import com.example.marketapp.features.order.data.entities.make_order_step_2.MakeOrderStep2Response
import com.example.marketapp.features.order.data.entities.order_details.OrderDetailsResponse
import com.example.marketapp.features.order.data.entities.orders.OrdersResponse
import com.example.marketapp.features.order.infrastructure.api.requests.CancelOrderRequest
import com.example.marketapp.features.order.infrastructure.api.requests.MakeOrderStep1Request
import com.example.marketapp.features.order.infrastructure.api.requests.MakeOrderStep2Request
import com.example.marketapp.features.order.infrastructure.api.requests.OrderDetailsRequest
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface OrderApi {

    @Multipart
    @POST("api/make-order")
    suspend fun makeOrderStep1(
        @Header("Authorization") token : String,
        @Part imageParts: List<MultipartBody.Part>,
        @Part recordParts: List<MultipartBody.Part>,
        @Part fileParts: List<MultipartBody.Part>,
        @Query("text") text: String,
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
    ): Response<MakeOrderStep1Response>


    @Headers("Content-Type: application/json")
    @POST("api/determine-delivery-address")
    suspend fun makeOrderStep2(
        @Header("Authorization") token : String,
        @Body makeOrderStep2Request: MakeOrderStep2Request
    ): Response<MakeOrderStep2Response>


    @Headers("Content-Type: application/json")
    @POST("api/cancel-order")
    suspend fun cancelOrder(
        @Header("Authorization") token : String,
        @Body cancelOrderRequest: CancelOrderRequest,
    ): Response<CancelOrderResponse>


    @Headers("Content-Type: application/json")
    @POST("api/order-details")
    suspend fun orderDetails(
        @Header("Authorization") token : String,
        @Body orderDetailsRequest: OrderDetailsRequest
    ): Response<OrderDetailsResponse>


    @Headers("Content-Type: application/json")
    @GET("api/orders?type=active")
    suspend fun orders(
        @Header("Authorization") token : String
    ): Response<OrdersResponse>


}