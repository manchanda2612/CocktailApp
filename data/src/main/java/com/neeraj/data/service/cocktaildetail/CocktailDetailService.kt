package com.neeraj.data.service.cocktaildetail

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import kotlinx.coroutines.flow.Flow

interface CocktailDetailService {
    suspend fun fetchCocktailDetail(cocktailId : String) : Flow<Resources<CocktailDetailModel>>

}