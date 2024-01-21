package com.sapi.domain.usecases.cocktaildetail

import com.sapi.common.network.Resources
import com.sapi.domain.model.cocktaildetail.CocktailDetail
import com.sapi.domain.repository.cocktaildetail.CocktailDetailRepository
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * Implementation of the [CocktailDetailUseCases] interface.
 * @property cocktailRepository The repository responsible for fetching detailed information about cocktails.
 */
class CocktailDetailUseCasesImpl @Inject constructor(
    private val cocktailRepository: CocktailDetailRepository
) : CocktailDetailUseCases {

    override suspend fun invoke(cocktailId : String): Resources<CocktailDetail> =
         cocktailRepository.fetchCocktailDetail(cocktailId)

}