package com.sapi.domain.di

import com.sapi.domain.repository.cocktaildetail.CocktailDetailRepository
import com.sapi.domain.repository.cocktaillist.CocktailListRepository
import com.sapi.domain.usecases.cocktaildetail.CocktailDetailUseCases
import com.sapi.domain.usecases.cocktaildetail.CocktailDetailUseCasesImpl
import com.sapi.domain.usecases.cocktaillist.CocktailListUseCases
import com.sapi.domain.usecases.cocktaillist.CocktailListUseCasesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * @author Neeraj Manchanda
 *
 * This class provide dependencies related to the domain layer such as use case.
 */
@InstallIn(ViewModelComponent::class)
@Module
class DomainModule {

    @Provides
    @ViewModelScoped
    fun provideCocktailListUseCase(cocktailRepository: CocktailListRepository): CocktailListUseCases =
        CocktailListUseCasesImpl(cocktailRepository)

    @Provides
    @ViewModelScoped
    fun provideCocktailDetailUseCase(cocktailRepository: CocktailDetailRepository): CocktailDetailUseCases =
        CocktailDetailUseCasesImpl(cocktailRepository)


}