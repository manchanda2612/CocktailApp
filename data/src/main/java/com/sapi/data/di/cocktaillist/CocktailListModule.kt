package com.sapi.data.di.cocktaillist

import com.sapi.data.repository.cocktaillist.CocktailListRepositoryImpl
import com.sapi.data.service.cocktaillist.CocktailListService
import com.sapi.data.service.cocktaillist.CocktailListServiceImpl
import com.sapi.domain.repository.cocktaillist.CocktailListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CocktailListModule {
    @Binds
    abstract fun bindCocktailListService(cocktailListServiceImpl: CocktailListServiceImpl): CocktailListService

    @Binds
    abstract fun bindCocktailListRepository(cocktailListRepositoryImpl: CocktailListRepositoryImpl): CocktailListRepository
}