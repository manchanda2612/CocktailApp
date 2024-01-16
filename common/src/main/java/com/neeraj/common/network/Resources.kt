package com.neeraj.common.network

import java.lang.Exception

/**
 * @author Neeraj Manchanda
 * This class is designed to wrap the results of network requests and provide a structured way to handle different scenarios
 * such as success, errors, and network-related issues.
 */
sealed class Resources<out T> {
    data class Success<out T>(val data: T) : Resources<T>()
    data class Failure<T>(val exception: Exception) : Resources<T>()
}
