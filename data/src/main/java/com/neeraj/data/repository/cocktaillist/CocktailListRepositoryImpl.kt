package com.neeraj.data.repository.cocktaillist

import com.neeraj.common.network.Resources
import com.neeraj.data.service.cocktaillist.CocktailListService
import com.neeraj.domain.model.cocktaillist.CocktailListModel
import com.neeraj.domain.repository.cocktaillist.CocktailListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Neeraj Manchanda
 * Implementation of the [CocktailListRepository] interface that interacts with
 * the [CocktailListService] to fetch a list of cocktails.
 *
 * @property cocktailListService Service responsible for fetching the list of cocktails from the API.
 */
class CocktailListRepositoryImpl @Inject constructor(
    private val cocktailListService: CocktailListService
) : CocktailListRepository {

    override suspend fun fetchCocktailList(): Flow<Resources<List<CocktailListModel>>> {
        return cocktailListService.fetchCocktailList()
    }

}