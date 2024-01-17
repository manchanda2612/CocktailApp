package com.neeraj.domain.repository.cocktaillist

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaillist.CocktailListModel
import kotlinx.coroutines.flow.Flow

/**
 * @author Neeraj Manchanda
 * Repository interface for fetching a list of cocktails.
 */
interface CocktailListRepository {
    suspend fun fetchCocktailList() : Flow<Resources<List<CocktailListModel>>>
}