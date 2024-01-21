package com.sapi.common.network

import java.lang.Exception

/**
 * @author Neeraj Manchanda
 * A sealed class representing the different states of a resource (e.g., data) retrieval operation.
 * Instances of this class can be used to encapsulate success or failure outcomes.
 *
 * The class is designed to be generic, allowing it to hold different types of data.
 *
 * @param T The type of data encapsulated by the resource.
 */
sealed class Resources<out T> {

    data class Success<out T>(val data: T) : Resources<T>()

    data class Failure<T>(val exception: Exception) : Resources<T>()

    object Loading : Resources<Nothing>()
}

