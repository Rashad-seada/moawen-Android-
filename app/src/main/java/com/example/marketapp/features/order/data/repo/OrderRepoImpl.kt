package com.example.marketapp.features.order.data.repo

import android.content.Context
import android.util.Log
import com.example.marketapp.R
import com.example.marketapp.core.errors.*
import com.example.marketapp.core.infrastructure.services.NetworkServiceImpl
import com.example.marketapp.core.util.Resource
import com.example.marketapp.core.util.uri_to_file.ToFile
import com.example.marketapp.features.order.data.data_source.remote.OrderRemoteDataSourceImpl
import com.example.marketapp.features.order.data.entities.cancel_order.CancelOrderResponse
import com.example.marketapp.features.order.data.entities.make_order_step_1.MakeOrderStep1Response
import com.example.marketapp.features.order.data.entities.make_order_step_2.MakeOrderStep2Response
import com.example.marketapp.features.order.data.entities.order_details.OrderDetailsResponse
import com.example.marketapp.features.order.data.entities.orders.OrdersResponse
import com.example.marketapp.features.order.domain.repo.OrderRepo
import com.example.marketapp.features.order.infrastructure.api.requests.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import javax.inject.Inject

class OrderRepoImpl @Inject constructor(
    val remoteDataSource: OrderRemoteDataSourceImpl,
    val networkService: NetworkServiceImpl,
) : OrderRepo {


    override suspend fun makeOrderStep1(
        token: String,
        makeOrderStep1Input: MakeOrderStep1Input,
        context: Context
    ): Resource<MakeOrderStep1Response> {
        try {

            val imageParts: List<MultipartBody.Part> = makeOrderStep1Input.imageParts.map { imageFile ->
                val requestFile =
                    imageFile.ToFile(context)?.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("images[]", imageFile.ToFile(context)?.name, requestFile!!)
            }
            Log.v("makeOrderStep1Response", "imageParts : ${imageParts.size}")



            val recordParts: List<MultipartBody.Part> = makeOrderStep1Input.recordParts.map { recordFile ->
                val requestFile =
                    recordFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("records[]", recordFile.name, requestFile)
            }
            Log.v("makeOrderStep1Response", "recordParts : ${recordParts.size}")

            val fileParts: List<MultipartBody.Part> = makeOrderStep1Input.fileParts.map { file ->
                val requestFile =
                    file.ToFile(context)?.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("files[]", file.ToFile(context)?.name, requestFile!!)
            }
            Log.v("makeOrderStep1Response", "fileParts : ${fileParts.size}")



            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = 0,
                        customCode = 0,
                    )
                )
            }

            val makeOrderStep1Response = remoteDataSource.makeOrderStep1(
                token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI3IiwianRpIjoiNmRhYTVkZTI1MDhmYzkyYzkyODJiOGRhZDNlMjg0ZTAwNGEzZTE2M2E4YmViODFkMDAyNzU0ZDE0M2I2Y2NkOWMxZDQ2ODllYzczYTFlNjMiLCJpYXQiOjE2OTU0MzA4MzIuMzkwNjI1LCJuYmYiOjE2OTU0MzA4MzIuMzkwNjMwMDA2NzkwMTYxMTMyODEyNSwiZXhwIjoxNzI3MDUzMjMyLjM4NzQzMjA5ODM4ODY3MTg3NSwic3ViIjoiMzMiLCJzY29wZXMiOlsidXNlciJdfQ.HRWUo2lHBsPkpUzAUcSy-86EIDFdendDWDBKpzrgJrkB98e1mi14cjYw_hmwjsnxNhwMtLl9tR-a4seStBiOMWoD0Xb6y68KFRHVBHhMWx4WPqjDOqlIVcs2bfs0W3mNSiwKjTTYLTyB-YJQqwhqi18_vfjUzsCmkj8T1hS_GreHontZXOeZS0G-vQ7AwuHVlvJ9yxiQNjH9oJ_dxKNC-86Iuz4ml_teAJzjgZn1AsJ91OQcDYeNf_PdRF2a3a8GcrMediCFGLXYTpOFnbqohqSlOIJqyGPF0bg-T4CiGQmorvTSyvFfCt64sAsh_1kWGYoUQSH7_EshLOwPF3yKGg",
                MakeOrderStep1Request(
                    imageParts,
                    recordParts,
                    fileParts,
                    makeOrderStep1Input.text,
                    makeOrderStep1Input.lat,
                    makeOrderStep1Input.lng
                )
            )


            when {

                !makeOrderStep1Response.isSuccessful -> {


                    val errorBody = makeOrderStep1Response.errorBody()


                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()

                        Log.v("makeOrderStep1Response", errorJson)

                        val jsonObjectError = JSONObject(errorJson)

                        if (jsonObjectError.has("errors")) {
                            val errorObj = jsonObjectError.getJSONObject("errors")

                            if (errorObj.has("text")) {
                                errorMessage = errorObj.getJSONArray("text").join(" and ")


                            } else if (errorObj.has("lat")) {
                                errorMessage = errorObj.getJSONArray("lat").join(" and ")

                            } else if (errorObj.has("lng")) {
                                errorMessage = errorObj.getJSONArray("lng").join(" and ")

                            } else {
                                errorMessage = context.getString(R.string.unknown_error)
                            }

                            makeOrderStep1Input.imageParts.forEachIndexed {
                                index, T -> if (errorObj.has("images.$index")) {
                                errorMessage = errorObj.getJSONArray("images.$index").join(" and ")
                                }
                            }

                            makeOrderStep1Input.fileParts.forEachIndexed { index, part ->
                                if (errorObj.has("files.$index")) {
                                errorMessage = errorObj.getJSONArray("files.$index").join(" and ")
                                }
                            }

                            makeOrderStep1Input.imageParts.forEachIndexed {
                                    index, T -> if (errorObj.has("records.$index")) {
                                errorMessage = errorObj.getJSONArray("records.$index").join(" and ")
                            }
                            }

                            return Resource.FailureData(
                                failure = ServiceFailure(
                                    message = errorMessage,
                                    screenId = 0,
                                    customCode = 0,
                                )
                            )
                        }else if(jsonObjectError.has("message")){
                            val errorObj = jsonObjectError.getString("message")
                            return Resource.FailureData(
                                failure = ServiceFailure(
                                    message = errorObj,
                                    screenId = 0,
                                    customCode = 0,
                                )
                            )
                        }
                    }
                }

                makeOrderStep1Response.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = 0,
                            customCode = 1,
                        )
                    )
                }

            }


            return Resource.SuccessData(
                data = makeOrderStep1Response.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun makeOrderStep2(
        token: String,
        makeOrderStep2Request: MakeOrderStep2Request,
        context: Context
    ): Resource<MakeOrderStep2Response> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = 0,
                        customCode = 0,
                    )
                )
            }

            val makeOrderStep2Response = remoteDataSource.makeOrderStep2(
                "Bearer $token",
                makeOrderStep2Request
            )


            when {

                !makeOrderStep2Response.isSuccessful -> {


                    val errorBody = makeOrderStep2Response.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()


                        val jsonObjectError = JSONObject(errorJson)

                        if(jsonObjectError.has("message")) {
                            errorMessage = jsonObjectError.getString("message")

                            return Resource.FailureData(
                                failure = ServiceFailure(
                                    message = errorMessage,
                                    screenId = 0,
                                    customCode = 0,
                                )
                            )

                        } else if (jsonObjectError.has("errors")) {
                            val errorObj = jsonObjectError.getJSONObject("errors")

                            if (errorObj.has("from_lat")) {
                                errorMessage = errorObj.getJSONArray("from_lat").join(" and ")


                            } else if (errorObj.has("from_lng")) {
                                errorMessage = errorObj.getJSONArray("from_lng").join(" and ")

                            } else if (errorObj.has("to_lat")) {
                                errorMessage = errorObj.getJSONArray("to_lat").join(" and ")

                            } else if (errorObj.has("to_lng")) {
                                errorMessage =
                                    errorObj.getJSONArray("to_lng").join(" and ")


                            } else if (errorObj.has("order_id")) {
                                errorMessage = errorObj.getJSONArray("order_id").join(" and ")

                            } else {
                                errorMessage = context.getString(R.string.unknown_error)
                            }

                            return Resource.FailureData(
                                failure = ServiceFailure(
                                    message = errorMessage,
                                    screenId = 0,
                                    customCode = 0,
                                )
                            )
                        }
                    }
                }

                makeOrderStep2Response.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = 0,
                            customCode = 1,
                        )
                    )
                }

            }


            return Resource.SuccessData(
                data = makeOrderStep2Response.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun cancelOrder(
        token: String,
        cancelOrderRequest: CancelOrderRequest,
        context: Context
    ): Resource<CancelOrderResponse> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = 0,
                        customCode = 0,
                    )
                )
            }

            val cancelOrderResponse = remoteDataSource.cancelOrder(
                token, cancelOrderRequest)



            when {

                !cancelOrderResponse.isSuccessful -> {


                    val errorBody = cancelOrderResponse.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()


                        val jsonObjectError = JSONObject(errorJson)

                        if(jsonObjectError.has("message")) {
                            errorMessage = jsonObjectError.getString("message")

                            return Resource.FailureData(
                                failure = ServiceFailure(
                                    message = errorMessage,
                                    screenId = 0,
                                    customCode = 0,
                                )
                            )

                        } else if (jsonObjectError.has("errors")) {
                            val errorObj = jsonObjectError.getJSONObject("errors")

                            if (errorObj.has("order_id")) {
                                errorMessage = errorObj.getJSONArray("order_id").join(" and ")

                            } else {
                                errorMessage = context.getString(R.string.unknown_error)
                            }

                            return Resource.FailureData(
                                failure = ServiceFailure(
                                    message = errorMessage,
                                    screenId = 0,
                                    customCode = 0,
                                )
                            )
                        }
                    }
                }

                cancelOrderResponse.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = 0,
                            customCode = 1,
                        )
                    )
                }

            }


            return Resource.SuccessData(
                data = cancelOrderResponse.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }

    override suspend fun orderDetails(
        token: String,
        orderDetailsRequest: OrderDetailsRequest,
        context: Context
    ): Resource<OrderDetailsResponse> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = 0,
                        customCode = 0,
                    )
                )
            }

            val orderDetailsResponse = remoteDataSource.orderDetails(
                token, orderDetailsRequest)



            when {

                !orderDetailsResponse.isSuccessful -> {


                    val errorBody = orderDetailsResponse.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()


                        val jsonObjectError = JSONObject(errorJson)

                        if(jsonObjectError.has("message")) {
                            errorMessage = jsonObjectError.getString("message")

                            return Resource.FailureData(
                                failure = ServiceFailure(
                                    message = errorMessage,
                                    screenId = 0,
                                    customCode = 0,
                                )
                            )

                        } else if (jsonObjectError.has("errors")) {
                            val errorObj = jsonObjectError.getJSONObject("errors")

                            if (errorObj.has("order_id")) {
                                errorMessage = errorObj.getJSONArray("order_id").join(" and ")

                            } else {
                                errorMessage = context.getString(R.string.unknown_error)
                            }

                            return Resource.FailureData(
                                failure = ServiceFailure(
                                    message = errorMessage,
                                    screenId = 0,
                                    customCode = 0,
                                )
                            )
                        }
                    }
                }

                orderDetailsResponse.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = 0,
                            customCode = 1,
                        )
                    )
                }

            }


            return Resource.SuccessData(
                data = orderDetailsResponse.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }


    override suspend fun orders(token: String, context: Context): Resource<OrdersResponse> {
        try {


            if (!networkService.isNetworkConnected(context)) {
                return Resource.FailureData(
                    failure = ServiceFailure(
                        message = context.getString(R.string.internet_connection),
                        screenId = 0,
                        customCode = 0,
                    )
                )
            }

            val orderDetailsResponse = remoteDataSource.orders(
                token)



            when {

                !orderDetailsResponse.isSuccessful -> {


                    val errorBody = orderDetailsResponse.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()


                        val jsonObjectError = JSONObject(errorJson)

                        if(jsonObjectError.has("message")) {
                            errorMessage = jsonObjectError.getString("message")
                        }

                        return Resource.FailureData(
                            failure = ServiceFailure(
                                message = errorMessage,
                                screenId = 0,
                                customCode = 0,
                            )
                        )
                    }
                }

                orderDetailsResponse.body() == null -> {
                    return Resource.FailureData(
                        failure = RemoteDataFailure(
                            message = context.getString(R.string.the_server_returned_null),
                            screenId = 0,
                            customCode = 1,
                        )
                    )
                }

            }


            return Resource.SuccessData(
                data = orderDetailsResponse.body()!!,
            )

        } catch (e: Exception) {
            val failure = when (e) {
                is ServiceException -> ServiceFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is RemoteDataException -> RemoteDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                is LocalDataException -> LocalDataFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )

                else -> InternalFailure(
                    e.message.toString(),
                    screenId = 0,
                    customCode = 0
                )
            }

            return Resource.FailureData(
                failure = failure
            )

        }
    }


}