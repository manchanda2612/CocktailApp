package com.neeraj.domain.repository.cocktaildetail

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import kotlinx.coroutines.flow.Flow

interface CocktailDetailRepository {
    suspend fun fetchCocktailDetail(cocktailId : String) : Flow<Resources<CocktailDetailModel>>

}