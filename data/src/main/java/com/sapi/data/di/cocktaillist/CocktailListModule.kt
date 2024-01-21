package com.sapi.data.di.cocktaillist

import com.sapi.common.util.InternetUtil
import com.sapi.data.mapper.cocktaillist.CocktailListMapper
import com.sapi.data.network.CocktailApiService
import com.sapi.data.repository.cocktaillist.CocktailListRepositoryImpl
import com.sapi.data.service.cocktaillist.CocktailListService
import com.sapi.data.service.cocktaillist.CocktailListServiceImpl
import com.sapi.domain.repository.cocktaillist.CocktailListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * @author Neeraj Manchanda
 * Cocktail List Module for providing dependencies related to the Cocktail list feature.
 * This module defines methods annotated with @Provides to offer instances of services
 * and repositories required for handling Cocktail list functionality within the application.
 *
 * @property CocktailApiService An implementation of [CocktailApiService] for fetching Cocktail list data from an API.
 * @property CocktailListMapper An instance of [CocktailListMapper] for mapping raw data to a structured Cocktail model list.
 * @property InternetUtil An instance of [InternetUtil] for checking internet connectivity.
 */
@InstallIn(ViewModelComponent::class)
@Module
object CocktailListModule {


    @Provides
    fun provideCocktailListService(
        cocktailApiService: CocktailApiService,
        cocktailListMapper: CocktailListMapper,
        internetUtil: InternetUtil
    ): CocktailListService =
        CocktailListServiceImpl(cocktailApiService, cocktailListMapper, internetUtil)

    @Provides
    fun provideCocktailListRepository(cocktailListService: CocktailListService): CocktailListRepository =
        CocktailListRepositoryImpl(cocktailListService)
}
