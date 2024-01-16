package com.neeraj.data.service.cocktaillist

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaillist.CocktailListModel
import kotlinx.coroutines.flow.Flow

interface CocktailListService {

    suspend fun fetchCocktailList() : Flow<Resources<List<CocktailListModel>>>

}