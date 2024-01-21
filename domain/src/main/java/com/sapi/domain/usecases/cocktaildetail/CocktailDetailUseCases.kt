package com.sapi.domain.usecases.cocktaildetail

import com.sapi.common.network.Resources
import com.sapi.domain.model.cocktaildetail.CocktailDetail

/**
 * @author Neeraj Manchanda
 * Use case interface for retrieving detailed information about a cocktail.
 */
interface CocktailDetailUseCases {
    suspend operator fun invoke(cocktailId : String) : Resources<CocktailDetail>
}