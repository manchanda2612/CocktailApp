package com.sapi.data.service.cocktaillist

import com.sapi.common.network.Resources
import com.sapi.domain.model.cocktaillist.CocktailList

/**
 * @author Neeraj Manchanda
 * Interface defining methods for fetching a list of cocktails from a remote data source.
 */
interface CocktailListService {
    suspend fun fetchCocktailList() : Resources<List<CocktailList>>
}