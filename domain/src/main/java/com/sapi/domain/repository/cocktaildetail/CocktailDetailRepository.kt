package com.sapi.domain.repository.cocktaildetail

import com.sapi.common.network.Resources
import com.sapi.domain.model.cocktaildetail.CocktailDetail

/**
 * @author Neeraj Manchanda
 * Repository interface for fetching detailed information about a cocktail.
 */
interface CocktailDetailRepository {
    suspend fun fetchCocktailDetail(cocktailId : String) : Resources<CocktailDetail>
}