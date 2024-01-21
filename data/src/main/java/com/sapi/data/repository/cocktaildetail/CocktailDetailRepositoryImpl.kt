package com.sapi.data.repository.cocktaildetail

import com.sapi.common.network.Resources
import com.sapi.data.service.cocktaildetail.CocktailDetailService
import com.sapi.domain.model.cocktaildetail.CocktailDetail
import com.sapi.domain.repository.cocktaildetail.CocktailDetailRepository
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 *
 * Implementation of the [CocktailDetailRepository] interface that interacts with
 * the [CocktailDetailService] to fetch details for a specific cocktail.
 * @property cocktailDetailService Service responsible for fetching cocktail details from the API.
 */
class CocktailDetailRepositoryImpl @Inject constructor(
    private val cocktailDetailService: CocktailDetailService
) : CocktailDetailRepository {


    override suspend fun fetchCocktailDetail(cocktailId : String): Resources<CocktailDetail> =
         cocktailDetailService.fetchCocktailDetail(cocktailId)

}