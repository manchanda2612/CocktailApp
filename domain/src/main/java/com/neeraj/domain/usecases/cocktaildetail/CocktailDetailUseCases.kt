package com.neeraj.domain.usecases.cocktaildetail

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.cocktaildetail.CocktailDetailModel
import kotlinx.coroutines.flow.Flow

interface CocktailDetailUseCases {
    suspend operator fun invoke(cocktailId : String) : Flow<Resources<CocktailDetailModel>>
}