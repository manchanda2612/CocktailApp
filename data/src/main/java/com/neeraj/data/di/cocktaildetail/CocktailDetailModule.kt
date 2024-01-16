package com.neeraj.data.di.cocktaildetail

import com.neeraj.common.util.InternetUtil
import com.neeraj.data.mapper.cocktaildetail.CocktailDetailMapper
import com.neeraj.data.network.CocktailApiService
import com.neeraj.data.repository.cocktaildetail.CocktailDetailRepositoryImpl
import com.neeraj.data.service.cocktaildetail.CocktailDetailService
import com.neeraj.data.service.cocktaildetail.CocktailDetailServiceImpl
import com.neeraj.domain.repository.cocktaildetail.CocktailDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object CocktailDetailModule {

    @Provides
    fun provideCocktailDetailService(cocktailApiService: CocktailApiService, cocktailDetailMapper: CocktailDetailMapper, internetUtil: InternetUtil) : CocktailDetailService =
        CocktailDetailServiceImpl(cocktailApiService, cocktailDetailMapper, internetUtil)

    @Provides
    fun provideCocktailDetailRepository(cocktailDetailService: CocktailDetailService) : CocktailDetailRepository =
        CocktailDetailRepositoryImpl(cocktailDetailService)
}