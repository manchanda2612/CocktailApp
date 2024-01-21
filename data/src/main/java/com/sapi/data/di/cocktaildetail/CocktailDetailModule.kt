package com.sapi.data.di.cocktaildetail

import com.sapi.common.util.InternetUtil
import com.sapi.data.mapper.cocktaildetail.CocktailDetailMapper
import com.sapi.data.network.CocktailApiService
import com.sapi.data.repository.cocktaildetail.CocktailDetailRepositoryImpl
import com.sapi.data.service.cocktaildetail.CocktailDetailService
import com.sapi.data.service.cocktaildetail.CocktailDetailServiceImpl
import com.sapi.domain.repository.cocktaildetail.CocktailDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * @author Neeraj Manchanda
 * Cocktail Detail Module for providing dependencies related to Cocktail details.
 * This module defines methods annotated with @Provides to offer instances of
 * services and repositories required for handling Cocktail details within the application.
 *
 * @property CocktailApiService An implementation of [CocktailApiService] for fetching Cocktail details from an API.
 * @property CocktailDetailMapper An instance of [CocktailDetailMapper] for mapping raw data to a structured CocktailDetail model.
 * @property InternetUtil An instance of [InternetUtil] for checking internet connectivity.
 */
@InstallIn(ViewModelComponent::class)
@Module
object CocktailDetailModule {

    @Provides
    fun provideCocktailDetailService(
        cocktailApiService: CocktailApiService,
        cocktailDetailMapper: CocktailDetailMapper,
        internetUtil: InternetUtil
    ): CocktailDetailService =
        CocktailDetailServiceImpl(cocktailApiService, cocktailDetailMapper, internetUtil)

    @Provides
    fun provideCocktailDetailRepository(cocktailDetailService: CocktailDetailService): CocktailDetailRepository =
        CocktailDetailRepositoryImpl(cocktailDetailService)
}
