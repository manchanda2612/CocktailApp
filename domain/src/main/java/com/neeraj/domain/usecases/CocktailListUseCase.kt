package com.neeraj.domain.usecases

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.CocktailListModel

interface CocktailListUseCase {

    suspend operator fun invoke() : Resources<List<CocktailListModel>>
}