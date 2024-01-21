package com.sapi.common.network

import java.lang.Exception

/**
 * @author Neeraj Manchanda
 * Custom exception class representing an API-related error,network-related error,data-related error
 *
 * @property code The HTTP status code associated with the API error.
 * @property message A readable message providing details about the API error.
 * @constructor Creates an instance of [ApiException] with the specified code and message.
 */
class ApiException(val code: Int, message: String?) : Exception(message)

class NetworkException(message: String?) : Exception(message)

class DataException(message: String?) : Exception(message)

