package com.neeraj.data.repository

import com.neeraj.common.network.Resources
import com.neeraj.data.service.CocktailService
import com.neeraj.domain.model.CocktailListModel
import com.neeraj.domain.repository.CocktailRepository
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val cocktailService: CocktailService
) : CocktailRepository {

    override suspend fun getCocktailList(): Resources<List<CocktailListModel>> {
        return cocktailService.getCocktailList()
    }
}