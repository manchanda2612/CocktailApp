package com.neeraj.common.di

import android.content.Context
import com.neeraj.common.util.InternetUtil
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

    /**
     * Provides the application context for dependency injection.
     *
     * @param context The application context provided by Dagger Hilt.
     * @return The application context to be used for dependency injection.
     */
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    /**
     * Provides an instance of [InternetUtil] for checking internet connectivity.
     *
     * @param context The application context to be used for initializing [InternetUtil].
     * @return An instance of [InternetUtil] for internet connectivity checks.
     */
    @Provides
    fun provideInternetUtil(context: Context): InternetUtil {
        return InternetUtil(context)
    }
}
