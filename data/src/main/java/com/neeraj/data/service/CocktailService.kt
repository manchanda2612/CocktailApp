package com.neeraj.data.service

import com.neeraj.common.network.Resources
import com.neeraj.domain.model.CocktailListModel

interface CocktailService {

    suspend fun getCocktailList() : Resources<List<CocktailListModel>>

}