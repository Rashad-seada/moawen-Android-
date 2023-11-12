package com.example.marketapp.features.wallet.data.repo

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.marketapp.R
import com.example.marketapp.core.errors.*
import com.example.marketapp.core.infrastructure.services.NetworkServiceImpl
import com.example.marketapp.core.util.Resource
import com.example.marketapp.core.util.uri_to_file.ToFile
import com.example.marketapp.features.wallet.data.data_source.remote.WalletRemoteDataSourceImpl
import com.example.marketapp.features.wallet.data.entities.charge_balance.ChargeBalanceResponse
import com.example.marketapp.features.wallet.data.entities.get_balance.GetBalanceResponse
import com.example.marketapp.features.wallet.domain.repo.WalletRepo
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import javax.inject.Inject

class WalletRepoImpl @Inject constructor(
    private val remoteDataSource: WalletRemoteDataSourceImpl,
    private val networkService: NetworkServiceImpl
) : WalletRepo{


    override suspend fun getBalance(
        token: String,
        context: Context
    ): Resource<GetBalanceResponse> {
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


            val makeOrderStep2Response = remoteDataSource.getBalance(
                "Bearer $token",
            )


            when {

                !makeOrderStep2Response.isSuccessful -> {


                    val errorBody = makeOrderStep2Response.errorBody()
                    var errorMessage = ""

                    if (errorBody != null) {
                        val errorJson = errorBody.string()

                        Log.v("getBalance",errorJson)


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
                        } else {
                            errorMessage = context.getString(R.string.unknown_error)
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

    override suspend fun chargeBalance(
        token: String,
        image: Uri,
        context: Context
    ): Resource<ChargeBalanceResponse> {
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

            val requestFile =
                image.ToFile(context)?.asRequestBody("multipart/form-data".toMediaTypeOrNull())


            val imagePart = MultipartBody.Part.createFormData("image", image.ToFile(context)?.name, requestFile!!)



            val makeOrderStep2Response = remoteDataSource.chargeBalance(
                "Bearer $token",
                imagePart
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
                        } else if(jsonObjectError.has("errors")) {
                            val errorObj = jsonObjectError.getJSONObject("errors")

                            if(errorObj.has("image")){
                                errorMessage = errorObj.getJSONArray("image")[0].toString()

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