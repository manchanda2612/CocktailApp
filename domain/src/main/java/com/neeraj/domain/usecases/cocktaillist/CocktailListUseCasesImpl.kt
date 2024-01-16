package com.neeraj.domain.usecases.cocktaillist

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaillist.CocktailListModel
import com.neeraj.domain.repository.cocktaillist.CocktailListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CocktailListUseCasesImpl @Inject constructor(
    private val cocktailRepository: CocktailListRepository
) : CocktailListUseCases {

    override suspend fun invoke(): Flow<Resources<List<CocktailListModel>>> {
        return cocktailRepository.fetchCocktailList()
    }
}