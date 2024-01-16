package com.neeraj.common.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject


/**
 * @author Neeraj Manchanda
 * Utility class for checking internet connectivity.
 *
 * This class provides a method to determine whether the device has an active internet connection
 * using the Android Connectivity Manager.
 *
 * @property applicationContext The application context used for accessing system services.
 * @constructor Creates an instance of [InternetUtil] with the specified application context.
 */
class InternetUtil @Inject constructor(private val applicationContext : Context) {

         fun isInternetAvailable(): Boolean {

            val connectivityManager = applicationContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
}
