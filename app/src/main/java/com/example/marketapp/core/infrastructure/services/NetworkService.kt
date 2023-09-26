package com.example.marketapp.core.infrastructure.services

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.marketapp.core.errors.ServiceException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

interface NetworkService {
    fun isNetworkConnected(context: Context): Boolean

    fun isInternetStable(): Boolean

}

class NetworkServiceImpl @Inject constructor(): NetworkService {

    override fun isNetworkConnected(context: Context): Boolean {
        try {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } catch (e: Exception) {
            throw ServiceException(e.message)
        }

    }

    override fun isInternetStable(): Boolean {
        val pingUrl = "https://www.ping.com" // You can use any reliable URL
        try {
            val url = URL(pingUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 3000 // Adjust the timeout as needed
            connection.connect()
            return connection.responseCode == 200
        } catch (e: IOException) {
            return false
        }
    }

}