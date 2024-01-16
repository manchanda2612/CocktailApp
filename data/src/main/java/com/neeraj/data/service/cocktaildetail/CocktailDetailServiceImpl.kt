package com.neeraj.data.service.cocktaildetail

import com.neeraj.common.constant.CommonConstant
import com.neeraj.common.network.ApiException
import com.neeraj.common.network.DataException
import com.neeraj.common.network.NetworkException
import com.neeraj.common.network.Resources
import com.neeraj.common.util.InternetUtil
import com.neeraj.data.mapper.cocktaildetail.CocktailDetailMapper
import com.neeraj.data.network.CocktailApiService
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CocktailDetailServiceImpl @Inject constructor(
    private val cocktailApiService: CocktailApiService,
    private val cocktailDetailMapper: CocktailDetailMapper,
    private val internetUtil: InternetUtil
) : CocktailDetailService {

    override suspend fun fetchCocktailDetail(cocktailId : String): Flow<Resources<CocktailDetailModel>> {
        return flow {
            val response = withContext(Dispatchers.IO) {
                if (internetUtil.isInternetAvailable()) {
                    try {
                        val cocktailDetailResponse = cocktailApiService.getCocktailDetail(cocktailId)
                        if (cocktailDetailResponse.isSuccessful) {
                            val cocktailList = cocktailDetailResponse.body()
                            return@withContext cocktailList?.let {
                                cocktailDetailMapper.getCocktailDetail(it)
                            } ?: Resources.Failure(
                                ApiException(
                                    cocktailDetailResponse.code(),
                                    cocktailDetailResponse.errorBody()?.toString()
                                        ?: CommonConstant.ErrorMessage
                                )
                            )
                        } else {
                            return@withContext Resources.Failure(
                                ApiException(
                                    cocktailDetailResponse.code(),
                                    cocktailDetailResponse.errorBody().toString()
                                )
                            )
                        }
                    } catch (exception: Exception) {
                        return@withContext Resources.Failure(
                            DataException(
                                exception.message ?: CommonConstant.ErrorMessage
                            )
                        )
                    }
                } else {
                    return@withContext Resources.Failure(NetworkException(CommonConstant.InternetErrorMessage))
                }
            }
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}
