package com.neeraj.data.service.cocktaildetail

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import kotlinx.coroutines.flow.Flow

/**
 * @author Neeraj Manchanda
 * Service interface responsible for fetching detailed information about a cocktail
 * from the remote data source.
 */
interface CocktailDetailService {
    suspend fun fetchCocktailDetail(cocktailId : String) : Flow<Resources<CocktailDetailModel>>

}