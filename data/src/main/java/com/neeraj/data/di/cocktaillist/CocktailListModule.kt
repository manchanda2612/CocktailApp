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

    /**
     * Provides an implementation of [CocktailListService] using the provided dependencies.
     *
     * @param cocktailApiService The service responsible for fetching Cocktail list data from an API.
     * @param cocktailListMapper The mapper for converting raw data to a structured Cocktail model list.
     * @param internetUtil The utility for checking internet connectivity.
     * @return An instance of [CocktailListService] for managing Cocktail list data.
     */
    @Provides
    fun provideCocktailListService(
        cocktailApiService: CocktailApiService,
        cocktailListMapper: CocktailListMapper,
        internetUtil: InternetUtil
    ): CocktailListService =
        CocktailListServiceImpl(cocktailApiService, cocktailListMapper, internetUtil)

    /**
     * Provides an implementation of [CocktailListRepository] using the provided [CocktailListService].
     *
     * @param cocktailListService The service for managing Cocktail list data.
     * @return An instance of [CocktailListRepository] for handling Cocktail list data.
     */
    @Provides
    fun provideCocktailListRepository(cocktailListService: CocktailListService): CocktailListRepository =
        CocktailListRepositoryImpl(cocktailListService)
}
