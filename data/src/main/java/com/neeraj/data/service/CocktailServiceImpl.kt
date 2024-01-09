package com.neeraj.data.service

import com.neeraj.common.constant.CommonConstant
import com.neeraj.common.network.ApiException
import com.neeraj.common.network.DataException
import com.neeraj.common.network.NetworkException
import com.neeraj.common.network.Resources
import com.neeraj.common.util.InternetUtil
import com.neeraj.data.mapper.CocktailListMapper
import com.neeraj.data.network.CocktailApiService
import com.neeraj.domain.model.CocktailListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CocktailServiceImpl @Inject constructor(
    private val cocktailApiService: CocktailApiService,
    private val cocktailListMapper: CocktailListMapper,
    private val internetUtil: InternetUtil
) : CocktailService  {

    override suspend fun getCocktailList(): Resources<List<CocktailListModel>> {
        return withContext(Dispatchers.IO) {
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
    }


}