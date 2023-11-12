package com.example.marketapp.features.notification.data.repo

import android.content.Context
import android.util.Log
import com.example.marketapp.R
import com.example.marketapp.core.errors.*
import com.example.marketapp.core.infrastructure.services.NetworkServiceImpl
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.notification.data.data_source.remote.NotificationRemoteDataSourceImpl
import com.example.marketapp.features.notification.data.entities.get_all_notification.GetAllNotificationsResponse
import com.example.marketapp.features.notification.data.entities.get_notification.GetNotificationResponse
import com.example.marketapp.features.notification.data.entities.get_notifications_count.GetNotificationsCountResponse
import com.example.marketapp.features.notification.domain.repo.NotificationRepo
import org.json.JSONObject
import javax.inject.Inject

class NotificationRepoImpl @Inject constructor(
    private val remoteDataSource: NotificationRemoteDataSourceImpl,
    private val networkService: NetworkServiceImpl,
    ) : NotificationRepo {


    override suspend fun getAllNotifications(
        token: String,
        context: Context
    ): Resource<GetAllNotificationsResponse> {
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

            val registerResponse = remoteDataSource.getAllNotifications(
                "Bearer $token"
            )



            when {

                !registerResponse.isSuccessful -> {

                    val errorBody = registerResponse.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()

                        val jsonObjectError = JSONObject(errorJson)

                        if (jsonObjectError.has("message")) {
                            errorMessage = jsonObjectError.getString("message").toString()
                        }else {
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

                registerResponse.body() == null -> {
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
                data = registerResponse.body()!!,
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

    override suspend fun getNotification(
        id: Int,
        token: String,
        context: Context
    ): Resource<GetNotificationResponse> {
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

            val registerResponse = remoteDataSource.getNotification(
                id = id,
                "Bearer $token"
            )



            when {

                !registerResponse.isSuccessful -> {

                    val errorBody = registerResponse.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()

                        val jsonObjectError = JSONObject(errorJson)

                        if (jsonObjectError.has("message")) {
                            errorMessage = jsonObjectError.getString("message").toString()
                        }else {
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

                registerResponse.body() == null -> {
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
                data = registerResponse.body()!!,
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

    override suspend fun getNotificationCount(
        token: String,
        context: Context
    ): Resource<GetNotificationsCountResponse> {
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

            val registerResponse = remoteDataSource.getNotificationCount(
                "Bearer $token"
            )



            when {

                !registerResponse.isSuccessful -> {

                    val errorBody = registerResponse.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()

                        val jsonObjectError = JSONObject(errorJson)

                        if (jsonObjectError.has("message")) {
                            errorMessage = jsonObjectError.getString("message").toString()
                        }else {
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

                registerResponse.body() == null -> {
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
                data = registerResponse.body()!!,
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




