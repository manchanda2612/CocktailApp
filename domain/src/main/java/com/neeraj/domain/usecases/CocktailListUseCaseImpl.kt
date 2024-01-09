package com.neeraj.domain.usecases

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.CocktailListModel
import com.neeraj.domain.repository.CocktailRepository
import javax.inject.Inject

class CocktailListUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : CocktailListUseCase {

    override suspend fun invoke(): Resources<List<CocktailListModel>> {
        return cocktailRepository.getCocktailList()
    }
}