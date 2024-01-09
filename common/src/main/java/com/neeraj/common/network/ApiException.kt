package com.neeraj.common.network

import java.lang.Exception

class ApiException(val code: Int, message: String?) : Exception(message)
class NetworkException(message: String?) : Exception(message)
class DataException(message: String?) : Exception(message)
