package com.sapi.presentation.route

import com.sapi.presentation.constant.UiConstants

/**
 * @author Neeraj Manchanda
 * Routes sealed class defines the different screens/routes in an app.
 * Each screen is represented by an object within the sealed class.
 */
sealed class Routes(val route: String) {
    object CocktailListScreen : Routes(UiConstants.cocktailListScreen)
    object CocktailDetailScreen : Routes(UiConstants.cocktailDetailScreen)
}