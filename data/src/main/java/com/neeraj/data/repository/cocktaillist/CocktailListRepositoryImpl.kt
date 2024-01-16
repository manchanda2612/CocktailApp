package com.neeraj.data.repository.cocktaillist

import com.neeraj.common.network.Resources
import com.neeraj.data.service.cocktaillist.CocktailListService
import com.neeraj.domain.model.cocktaillist.CocktailListModel
import com.neeraj.domain.repository.cocktaillist.CocktailListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CocktailListRepositoryImpl @Inject constructor(
    private val cocktailListService: CocktailListService
) : CocktailListRepository {

    override suspend fun fetchCocktailList(): Flow<Resources<List<CocktailListModel>>> {
        return cocktailListService.fetchCocktailList()
    }

}