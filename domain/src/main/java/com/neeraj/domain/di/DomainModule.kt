package com.neeraj.domain.di.cocktaillist

import com.neeraj.domain.repository.cocktaildetail.CocktailDetailRepository
import com.neeraj.domain.repository.cocktaillist.CocktailListRepository
import com.neeraj.domain.usecases.cocktaildetail.CocktailDetailUseCases
import com.neeraj.domain.usecases.cocktaildetail.CocktailDetailUseCasesImpl
import com.neeraj.domain.usecases.cocktaillist.CocktailListUseCases
import com.neeraj.domain.usecases.cocktaillist.CocktailListUseCasesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * @author Neeraj Manchanda
 *
 * This class provide dependencies related to the domain layer.
 */
@InstallIn(ViewModelComponent::class)
@Module
class DomainModule {

    @Provides
    fun provideCocktailListUseCase(cocktailRepository: CocktailListRepository): CocktailListUseCases =
        CocktailListUseCasesImpl(cocktailRepository)


    @Provides
    fun provideCocktailDetailUseCase(cocktailRepository: CocktailDetailRepository): CocktailDetailUseCases =
        CocktailDetailUseCasesImpl(cocktailRepository)


}