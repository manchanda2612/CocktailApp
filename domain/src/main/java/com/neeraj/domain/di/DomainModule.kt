package com.neeraj.domain.di

import com.neeraj.domain.repository.CocktailRepository
import com.neeraj.domain.usecases.CocktailListUseCaseImpl
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
    fun provideCocktailListUseCase(cocktailRepository: CocktailRepository) = CocktailListUseCaseImpl(cocktailRepository)


}