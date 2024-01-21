package com.sapi.data.base

import com.sapi.common.constant.CommonConstant
import com.sapi.common.network.ApiException
import com.sapi.common.network.DataException
import com.sapi.common.network.NetworkException
import com.sapi.common.network.Resources
import com.sapi.common.util.InternetUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * @author Neeraj Manchanda
 * BaseService is an abstract class providing a common framework for making API calls.
 * It incorporates network availability checks and handles various exceptions during the API call.
 *
 * @constructor Creates a BaseService instance.
 */
abstract class BaseService {

    suspend fun <T, R> hitApi(
        internetUtil: InternetUtil,
        apiToBeCalled: suspend () -> Response<T>,
        mapper: (Response<T>) -> R
    ): Resources<R> {

        return withContext(Dispatchers.IO) {

            if (internetUtil.isInternetAvailable()) {
                try {

                    val response: Response<T> = apiToBeCalled()

                    if (response.isSuccessful) {
                        response.body()?.let { Resources.Success(mapper.invoke(response)) }
                            ?: Resources.Failure(
                                ApiException(
                                    response.code(),
                                    response.message().toString()
                                )
                            )

                    } else {
                        Resources.Failure(
                            ApiException(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }

                } catch (e: Exception) {
                    Resources.Failure(DataException(CommonConstant.ErrorMessage))
                }
            } else {
                Resources.Failure(NetworkException(CommonConstant.InternetErrorMessage))
            }
        }
    }
}
