package com.example.marketapp.features.profile.data.repo

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.marketapp.R
import com.example.marketapp.core.errors.*
import com.example.marketapp.core.infrastructure.services.NetworkServiceImpl
import com.example.marketapp.core.util.FileHelper
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.profile.data.data_source.remote.ProfileRemoteDataSourceImpl
import com.example.marketapp.features.profile.data.entities.update_phone_number.UpdatePhoneNumberResponse
import com.example.marketapp.features.profile.data.entities.update_phone_number_step_2.UpdatePhoneNumberStep2Response
import com.example.marketapp.features.profile.data.entities.update_profile_name_and_image.UpdateProfileNameAndImageResponse
import com.example.marketapp.features.profile.domain.repo.ProfileRepo
import com.example.marketapp.features.profile.infrastructure.api.request.UpdatePhoneNumberRequest
import com.example.marketapp.features.profile.infrastructure.api.request.UpdatePhoneNumberStep2Request
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.apache.commons.io.FileUtils
import org.json.JSONObject
import java.io.File
import javax.inject.Inject

class ProfileRepoImpl @Inject constructor(
    private val remoteDataSource: ProfileRemoteDataSourceImpl,
    private val networkService: NetworkServiceImpl,
) : ProfileRepo {


    override suspend fun updateProfileNameAndImage(
        token: String,
        fullName: String?,
        imagePart: Uri?,
        context: Context
    ): Resource<UpdateProfileNameAndImageResponse> {

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

            val queryParameters = mutableMapOf<String, Any>()


            if (imagePart != null) {


                val file = File(imagePart.path)

                val requestFile: RequestBody = file.asRequestBody(MultipartBody.FORM)

                val imagePart = MultipartBody.Part.createFormData(
                    "image",
                    file.name,
                    requestFile
                )

                queryParameters.put(
                    "image" , imagePart,
                )

            }

            if(fullName != null){
                queryParameters.put(
                    "fullname" , fullName,
                )

            }



            val makeOrderStep2Response = remoteDataSource.updateProfileNameAndImage(
                "Bearer $token",
                queryParameters
            )


            when {

                !makeOrderStep2Response.isSuccessful -> {


                    val errorBody = makeOrderStep2Response.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {

                        val errorJson = errorBody.string()

                        val jsonObjectError = JSONObject(errorJson)

                        if(jsonObjectError.has("errors")){
                            val errorObj = jsonObjectError.getJSONObject("errors")

                            if(errorObj.has("image")){
                                errorMessage = errorObj.getJSONArray("image")[0].toString()

                            }
                        }

                        else if (jsonObjectError.has("message")) {
                            errorMessage = jsonObjectError.getString("message")

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

    override suspend fun updatePhoneNumber(
        token: String,
        updatePhoneNumberRequest: UpdatePhoneNumberRequest,
        context: Context
    ): Resource<UpdatePhoneNumberResponse> {
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


            val makeOrderStep2Response = remoteDataSource.updatePhoneNumber(
                "Bearer $token",
                updatePhoneNumberRequest,
            )


            when {

                !makeOrderStep2Response.isSuccessful -> {


                    val errorBody = makeOrderStep2Response.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()


                        val jsonObjectError = JSONObject(errorJson)

                        if (jsonObjectError.has("message")) {
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

                            if (errorObj.has("country_code")) {
                                errorMessage = errorObj.getJSONArray("country_code")[0].toString()

                            } else if (errorObj.has("phone")) {
                                errorMessage = errorObj.getJSONArray("phone")[0].toString()

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

    override suspend fun updatePhoneNumberStep2(
        token: String,
        updatePhoneNumberStep2Request: UpdatePhoneNumberStep2Request,
        context: Context
    ): Resource<UpdatePhoneNumberStep2Response> {
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


            val makeOrderStep2Response = remoteDataSource.updatePhoneNumberStep2(
                "Bearer $token",
                updatePhoneNumberStep2Request,
            )


            when {

                !makeOrderStep2Response.isSuccessful -> {


                    val errorBody = makeOrderStep2Response.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()


                        val jsonObjectError = JSONObject(errorJson)

                        if (jsonObjectError.has("message")) {
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

                            if (errorObj.has("country_code")) {
                                errorMessage = errorObj.getJSONArray("country_code")[0].toString()

                            } else if (errorObj.has("otp")) {
                                errorMessage = errorObj.getJSONArray("otp")[0].toString()

                            } else if (errorObj.has("phone")) {
                                errorMessage = errorObj.getJSONArray("phone")[0].toString()

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


}