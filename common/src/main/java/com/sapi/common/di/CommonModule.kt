package com.sapi.common.di

import android.content.Context
import com.sapi.common.util.InternetUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * @author Neeraj Manchanda
 * Common Module for providing common dependencies used throughout the application.
 * This module includes methods annotated with @Provides to offer instances of commonly
 * used classes such as Context and InternetUtil.
 *
 * @property context The application context provided by Dagger Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object CommonModule {
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context = context
    @Provides
    fun provideInternetUtil(context: Context): InternetUtil = InternetUtil(context)
}
