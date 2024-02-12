package com.sapi.data.service.cocktaildetail


import com.sapi.common.network.Resources
import com.sapi.common.util.InternetUtil
import com.sapi.data.base.BaseService
import com.sapi.data.mapper.cocktaildetail.CocktailDetailMapper
import com.sapi.data.network.CocktailApiService
import com.sapi.domain.model.cocktaildetail.CocktailDetail
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * Implementation of [CocktailDetailService] responsible for fetching detailed information about a cocktail
 * from the remote data source using the provided [CocktailApiService], [CocktailDetailMapper], and [InternetUtil].
 *
 * @param cocktailApiService The service providing API calls related to cocktails.
 * @param cocktailDetailMapper The mapper responsible for converting API responses to [CocktailDetail].
 * @param internetUtil The utility class for checking internet connectivity.
 */
class CocktailDetailServiceImpl @Inject constructor(
    private val cocktailApiService: CocktailApiService,
    private val dispatcher : CoroutineDispatcher,
    private val cocktailDetailMapper: CocktailDetailMapper,
    private val internetUtil: InternetUtil
) : BaseService(), CocktailDetailService {

    override suspend fun fetchCocktailDetail(cocktailId : String): Resources<CocktailDetail> =

        hitApiCall(
            apiToBeCalled = { cocktailApiService.getCocktailDetail(cocktailId) },
            dispatcher = dispatcher,
            mapper = { response -> cocktailDetailMapper.getCocktailDetail(response.body()!!)  },
            internetUtil = internetUtil
        )

}