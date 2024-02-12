package com.sapi.data.service.cocktaillist

import com.sapi.common.network.Resources
import com.sapi.common.util.InternetUtil
import com.sapi.data.base.BaseService
import com.sapi.data.mapper.cocktaillist.CocktailListMapper
import com.sapi.data.network.CocktailApiService
import com.sapi.domain.model.cocktaillist.CocktailList
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * Implementation of [CocktailListService] that fetches a list of cocktails from a remote data source.
 *
 * @param cocktailApiService The service responsible for making API calls related to cocktails.
 * @param cocktailListMapper Mapper to transform the API response to a list of [CocktailList].
 * @param internetUtil Utility class to check for internet connectivity.
 */
class CocktailListServiceImpl @Inject constructor(
    private val cocktailApiService: CocktailApiService,
    private val dispatcher : CoroutineDispatcher,
    private val cocktailListMapper: CocktailListMapper,
    private val internetUtil: InternetUtil
) : BaseService(), CocktailListService {

    override suspend fun fetchCocktailList() : Resources<List<CocktailList>> =

    hitApiCall(
        apiToBeCalled = { cocktailApiService.getCocktailList() },
        dispatcher = dispatcher,
        mapper = { response -> cocktailListMapper.getCocktailList(response.body()!!)  },
        internetUtil = internetUtil
    )
}