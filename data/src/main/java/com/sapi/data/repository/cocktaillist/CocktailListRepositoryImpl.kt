package com.sapi.data.repository.cocktaillist

import com.sapi.common.network.Resources
import com.sapi.data.service.cocktaillist.CocktailListService
import com.sapi.domain.model.cocktaillist.CocktailList
import com.sapi.domain.repository.cocktaillist.CocktailListRepository
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

    override suspend fun fetchCocktailList(): Resources<List<CocktailList>> =
        cocktailListService.fetchCocktailList()


}