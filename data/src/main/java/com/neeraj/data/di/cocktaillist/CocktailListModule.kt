package com.neeraj.data.di.cocktaillist

import com.neeraj.common.util.InternetUtil
import com.neeraj.data.mapper.cocktaillist.CocktailListMapper
import com.neeraj.data.network.CocktailApiService
import com.neeraj.data.repository.cocktaillist.CocktailListRepositoryImpl
import com.neeraj.data.service.cocktaillist.CocktailListService
import com.neeraj.data.service.cocktaillist.CocktailListServiceImpl
import com.neeraj.domain.repository.cocktaillist.CocktailListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object CocktailListModule {

    @Provides
    fun provideCocktailListService(cocktailApiService: CocktailApiService, cocktailListMapper: CocktailListMapper, internetUtil: InternetUtil) : CocktailListService =
        CocktailListServiceImpl(cocktailApiService, cocktailListMapper, internetUtil)

    @Provides
    fun provideCocktailListRepository(cocktailListService: CocktailListService) : CocktailListRepository =
        CocktailListRepositoryImpl(cocktailListService)
}