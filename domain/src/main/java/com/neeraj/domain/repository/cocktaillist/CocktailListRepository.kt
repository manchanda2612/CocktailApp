package com.neeraj.domain.repository.cocktaillist

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaillist.CocktailListModel
import kotlinx.coroutines.flow.Flow

interface CocktailListRepository {
    suspend fun fetchCocktailList() : Flow<Resources<List<CocktailListModel>>>
}