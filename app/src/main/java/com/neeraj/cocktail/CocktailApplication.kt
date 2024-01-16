package com.neeraj.cocktail


import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * @author Neeraj Manchanda
 * Main Application class for the Cocktail App.
 *
 * This class is annotated with `@HiltAndroidApp`, indicating that it is the entry point
 * for Hilt's dependency injection in the Cocktail App. Hilt will generate the necessary
 * components and handle dependency injection based on the annotations used within the app.
 *
 * When extending the [Application] class, it allows customization of the Android application's
 * initialization.
 */
@HiltAndroidApp
class CocktailApplication : Application()
