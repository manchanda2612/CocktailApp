package com.sapi.data.di.cocktaildetail

import com.sapi.data.repository.cocktaildetail.CocktailDetailRepositoryImpl
import com.sapi.data.service.cocktaildetail.CocktailDetailService
import com.sapi.data.service.cocktaildetail.CocktailDetailServiceImpl
import com.sapi.domain.repository.cocktaildetail.CocktailDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CocktailDetailModules {
    @Binds
   abstract fun bindCocktailDetailService(cocktailDetailServiceImpl: CocktailDetailServiceImpl) : CocktailDetailService

   @Binds
   abstract fun bindCocktailDetailRepository(cocktailDetailRepositoryImpl: CocktailDetailRepositoryImpl) : CocktailDetailRepository
}