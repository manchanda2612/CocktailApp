package com.sapi.domain.usecases.cocktaillist

import com.sapi.common.network.Resources
import com.sapi.domain.model.cocktaillist.CocktailList

/**
 * @author Neeraj Manchanda
 * Use case interface for retrieving list information about a cocktail.
 */
interface CocktailListUseCases {
    suspend operator fun invoke() : Resources<List<CocktailList>>
}