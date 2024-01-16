package com.neeraj.data.service.cocktaillist

import com.neeraj.common.constant.CommonConstant
import com.neeraj.common.network.ApiException
import com.neeraj.common.network.DataException
import com.neeraj.common.network.NetworkException
import com.neeraj.common.network.Resources
import com.neeraj.common.util.InternetUtil
import com.neeraj.data.mapper.cocktaillist.CocktailListMapper
import com.neeraj.data.network.CocktailApiService
import com.neeraj.domain.model.cocktaillist.CocktailListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CocktailListServiceImpl @Inject constructor(
    private val cocktailApiService: CocktailApiService,
    private val cocktailListMapper: CocktailListMapper,
    private val internetUtil: InternetUtil
) : CocktailListService {

    override suspend fun fetchCocktailList() : Flow<Resources<List<CocktailListModel>>> {
        return flow {
            val response = withContext(Dispatchers.IO) {
                if(internetUtil.isInternetAvailable()) {
                    try {
                        val cocktailListResponse = cocktailApiService.getCocktailList()
                        if(cocktailListResponse.isSuccessful) {
                            val cocktailList = cocktailListResponse.body()
                            return@withContext cocktailList?.let {
                                cocktailListMapper.getCocktailList(it)
                            } ?: Resources.Failure(ApiException(cocktailListResponse.code(),
                                cocktailListResponse.errorBody()?.toString() ?: CommonConstant.ErrorMessage))
                        } else {
                            return@withContext Resources.Failure(ApiException(cocktailListResponse.code(),
                                cocktailListResponse.errorBody().toString()))
                        }
                    } catch (exception : Exception)  {
                        return@withContext Resources.Failure(DataException(exception.message ?: CommonConstant.ErrorMessage))
                    }
                } else {
                    return@withContext Resources.Failure(NetworkException(CommonConstant.InternetErrorMessage))
                }
            }
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

}