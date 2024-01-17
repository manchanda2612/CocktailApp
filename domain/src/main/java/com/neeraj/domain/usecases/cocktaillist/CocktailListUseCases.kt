package com.neeraj.domain.usecases.cocktaillist

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaillist.CocktailListModel
import kotlinx.coroutines.flow.Flow

/**
 * @author Neeraj Manchanda
 * Use case interface for retrieving list information about a cocktail.
 */
interface CocktailListUseCases {
    suspend operator fun invoke() : Flow<Resources<List<CocktailListModel>>>
}