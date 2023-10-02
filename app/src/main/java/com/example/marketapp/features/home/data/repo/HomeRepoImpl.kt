package com.example.marketapp.features.home.data.repo

import android.content.Context
import android.util.Log
import com.example.marketapp.R
import com.example.marketapp.core.errors.*
import com.example.marketapp.core.infrastructure.services.NetworkServiceImpl
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.home.data.data_source.remote.HomeRemoteDataSourceImpl
import com.example.marketapp.features.home.data.entities.home.HomeResponse
import com.example.marketapp.features.home.domain.repo.HomeRepo
import org.json.JSONObject
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(
    val remoteDataSource: HomeRemoteDataSourceImpl,
    val networkService: NetworkServiceImpl,
) : HomeRepo {

    override suspend fun home(token: String,context: Context): Resource<HomeResponse> {

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

            val registerResponse = remoteDataSource.home(
                "Bearer $token"
            )



            when {

                !registerResponse.isSuccessful -> {
                    Log.v("Home", "point 1")


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