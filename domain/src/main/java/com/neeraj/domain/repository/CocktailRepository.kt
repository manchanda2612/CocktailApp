package com.neeraj.domain.repository

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.CocktailListModel

interface CocktailRepository {

    suspend fun getCocktailList() : Resources<List<CocktailListModel>>

}