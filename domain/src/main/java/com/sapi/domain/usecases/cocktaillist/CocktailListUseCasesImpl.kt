package com.sapi.domain.usecases.cocktaillist

import com.sapi.common.network.Resources
import com.sapi.domain.model.cocktaillist.CocktailList
import com.sapi.domain.repository.cocktaillist.CocktailListRepository
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * Implementation of the [CocktailListUseCases] interface.
 *
 * @property cocktailRepository The repository responsible for fetching a list of cocktails.
 */
class CocktailListUseCasesImpl @Inject constructor(
    private val cocktailRepository: CocktailListRepository
) : CocktailListUseCases {

    override suspend fun invoke(): Resources<List<CocktailList>> =
         cocktailRepository.fetchCocktailList()
}