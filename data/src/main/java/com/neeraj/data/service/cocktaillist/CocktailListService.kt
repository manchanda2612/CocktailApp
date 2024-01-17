package com.neeraj.data.service.cocktaillist

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaillist.CocktailListModel
import kotlinx.coroutines.flow.Flow

/**
 * @author Neeraj Manchanda
 * Interface defining methods for fetching a list of cocktails from a remote data source.
 */
interface CocktailListService {

    suspend fun fetchCocktailList() : Flow<Resources<List<CocktailListModel>>>

}