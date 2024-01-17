package com.neeraj.domain.usecases.cocktaildetail

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import com.neeraj.domain.repository.cocktaildetail.CocktailDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * Implementation of the [CocktailDetailUseCases] interface.
 * @property cocktailRepository The repository responsible for fetching detailed information about cocktails.
 */
class CocktailDetailUseCasesImpl @Inject constructor(
    private val cocktailRepository: CocktailDetailRepository
) : CocktailDetailUseCases {

    override suspend fun invoke(cocktailId : String): Flow<Resources<CocktailDetailModel>> {
        return cocktailRepository.fetchCocktailDetail(cocktailId)
    }
}