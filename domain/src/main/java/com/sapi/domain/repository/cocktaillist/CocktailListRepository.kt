package com.sapi.domain.repository.cocktaillist

import com.sapi.common.network.Resources
import com.sapi.domain.model.cocktaillist.CocktailList

/**
 * @author Neeraj Manchanda
 * Repository interface for fetching a list of cocktails.
 */
interface CocktailListRepository {
    suspend fun fetchCocktailList() : Resources<List<CocktailList>>
}