package com.neeraj.domain.repository.cocktaildetail

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import kotlinx.coroutines.flow.Flow

/**
 * @author Neeraj Manchanda
 * Repository interface for fetching detailed information about a cocktail.
 */
interface CocktailDetailRepository {
    suspend fun fetchCocktailDetail(cocktailId : String) : Flow<Resources<CocktailDetailModel>>

}