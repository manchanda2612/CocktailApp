package com.neeraj.domain.usecases.cocktaildetail

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import com.neeraj.domain.model.cocktaillist.CocktailListModel
import com.neeraj.domain.repository.cocktaildetail.CocktailDetailRepository
import com.neeraj.domain.repository.cocktaillist.CocktailListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CocktailDetailUseCasesImpl @Inject constructor(
    private val cocktailRepository: CocktailDetailRepository
) : CocktailDetailUseCases {

    override suspend fun invoke(cocktailId : String): Flow<Resources<CocktailDetailModel>> {
        return cocktailRepository.fetchCocktailDetail(cocktailId)
    }
}