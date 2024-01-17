package com.neeraj.domain.usecases.cocktaildetail

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import kotlinx.coroutines.flow.Flow

/**
 * @author Neeraj Manchanda
 * Use case interface for retrieving detailed information about a cocktail.
 */
interface CocktailDetailUseCases {
    suspend operator fun invoke(cocktailId : String) : Flow<Resources<CocktailDetailModel>>
}