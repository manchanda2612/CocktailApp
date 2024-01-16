package com.neeraj.common.network

import java.lang.Exception

/**
 * @author Neeraj Manchanda
 * Custom exception class representing an API-related error.
 *
 * @property code The HTTP status code associated with the API error.
 * @property message A readable message providing details about the API error.
 * @constructor Creates an instance of [ApiException] with the specified code and message.
 */
class ApiException(val code: Int, message: String?) : Exception(message)

/**
 * Custom exception class representing a network-related error.
 *
 * @property message A readable message providing details about the network error.
 * @constructor Creates an instance of [NetworkException] with the specified message.
 */
class NetworkException(message: String?) : Exception(message)

/**
 * Custom exception class representing a data-related error.
 *
 * @property message A readable message providing details about the data error.
 * @constructor Creates an instance of [DataException] with the specified message.
 */
class DataException(message: String?) : Exception(message)

