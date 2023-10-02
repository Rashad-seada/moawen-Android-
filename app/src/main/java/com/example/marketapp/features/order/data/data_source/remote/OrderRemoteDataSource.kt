package com.example.marketapp.features.order.data.data_source.remote

import com.example.marketapp.core.errors.RemoteDataException
import com.example.marketapp.features.order.data.entities.cancel_order.CancelOrderResponse
import com.example.marketapp.features.order.data.entities.make_order_step_1.MakeOrderStep1Response
import com.example.marketapp.features.order.data.entities.make_order_step_2.MakeOrderStep2Response
import com.example.marketapp.features.order.data.entities.order_details.OrderDetailsResponse
import com.example.marketapp.features.order.data.entities.orders.OrdersResponse
import com.example.marketapp.features.order.infrastructure.api.OrderApi
import com.example.marketapp.features.order.infrastructure.api.requests.CancelOrderRequest
import com.example.marketapp.features.order.infrastructure.api.requests.MakeOrderStep1Request
import com.example.marketapp.features.order.infrastructure.api.requests.MakeOrderStep2Request
import com.example.marketapp.features.order.infrastructure.api.requests.OrderDetailsRequest
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import javax.inject.Inject

interface OrderRemoteDataSource {


    suspend fun makeOrderStep1(
        token: String,
        makeOrderStep1Request: MakeOrderStep1Request
    ): Response<MakeOrderStep1Response>


    suspend fun makeOrderStep2(
        token: String,
        makeOrderStep2Request: MakeOrderStep2Request
    ): Response<MakeOrderStep2Response>


    suspend fun cancelOrder(
        token: String,
        cancelOrderRequest: CancelOrderRequest,
    ): Response<CancelOrderResponse>


    suspend fun orderDetails(
        token: String,
        orderDetailsRequest: OrderDetailsRequest
    ): Response<OrderDetailsResponse>


    suspend fun orders(
        token: String
    ): Response<OrdersResponse>


}

class OrderRemoteDataSourceImpl @Inject constructor(val api: OrderApi) : OrderRemoteDataSource {

    override suspend fun makeOrderStep1(
        token: String,
        makeOrderStep1Request: MakeOrderStep1Request

    ): Response<MakeOrderStep1Response> {
        try {


            return api.makeOrderStep1(
                token,
                imageParts = makeOrderStep1Request.imageParts,
                recordParts =  makeOrderStep1Request.recordParts,
                fileParts =  makeOrderStep1Request.fileParts,
                text = makeOrderStep1Request.text,
                lat = makeOrderStep1Request.lat,
                lng = makeOrderStep1Request.lng
            )

        } catch (e: Exception) {
            throw RemoteDataException(e.message.toString())

        }
    }

    override suspend fun makeOrderStep2(
        token: String,
        makeOrderStep2Request: MakeOrderStep2Request
    ): Response<MakeOrderStep2Response> {
        try {
            return api.makeOrderStep2(
                token,
                makeOrderStep2Request
            )

        } catch (e: Exception) {
            throw RemoteDataException(e.message.toString())

        }
    }

    override suspend fun cancelOrder(
        token: String,
        cancelOrderRequest: CancelOrderRequest
    ): Response<CancelOrderResponse> {
        try {
            return api.cancelOrder(
                token,
                cancelOrderRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(e.message.toString())

        }
    }

    override suspend fun orderDetails(
        token: String,
        orderDetailsRequest: OrderDetailsRequest
    ): Response<OrderDetailsResponse> {
        try {
            return api.orderDetails(
                token,
                orderDetailsRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(e.message.toString())

        }
    }

    override suspend fun orders(token: String): Response<OrdersResponse> {
        try {
            return api.orders(
                token = token
            )

        } catch (e: Exception) {
            throw RemoteDataException(e.message.toString())

        }
    }

}