package com.neeraj.common.network

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
    /**
     * Represents the successful outcome of a resource retrieval operation.
     *
     * @property data The retrieved data of type [T].
     */
    data class Success<out T>(val data: T) : Resources<T>()

    /**
     * Represents the failure outcome of a resource retrieval operation.
     *
     * @property exception The exception providing details about the failure.
     */
    data class Failure<T>(val exception: Exception) : Resources<T>()
}

