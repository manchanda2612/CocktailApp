package com.sapi.data.service.cocktaildetail

import com.sapi.common.network.Resources
import com.sapi.domain.model.cocktaildetail.CocktailDetail

/**
 * @author Neeraj Manchanda
 * Service interface responsible for fetching detailed information about a cocktail
 * from the remote data source.
 */
interface CocktailDetailService {
    suspend fun fetchCocktailDetail(cocktailId : String) : Resources<CocktailDetail>

}