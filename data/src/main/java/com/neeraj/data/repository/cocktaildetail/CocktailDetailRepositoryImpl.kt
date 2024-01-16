package com.neeraj.data.repository.cocktaildetail

import com.neeraj.common.network.Resources
import com.neeraj.data.service.cocktaildetail.CocktailDetailService
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import com.neeraj.domain.repository.cocktaildetail.CocktailDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CocktailDetailRepositoryImpl @Inject constructor(
    private val cocktailDetailService: CocktailDetailService
) : CocktailDetailRepository {

    override suspend fun fetchCocktailDetail(cocktailId : String): Flow<Resources<CocktailDetailModel>> {
        return cocktailDetailService.fetchCocktailDetail(cocktailId)
    }
}