package com.neeraj.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neeraj.presentation.constant.UiConstants
import com.neeraj.presentation.screens.CocktailListScreen
import com.neeraj.presentation.route.Routes
import com.neeraj.presentation.screens.CocktailDetailScreen


/**
 * @author Neeraj Manchanda
 * This function define a navigation graph using Navigation component.
 * It sets up navigation between two destinations: a book list screen and a book detail screen.
 */
@Composable
fun NavigationGraph() {

    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Routes.CocktailListScreen.route) {

        composable(Routes.CocktailListScreen.route) {
            CocktailListScreen(navController)
        }

        composable(Routes.CocktailDetailScreen.route + "/{cocktailId}") { navBackStackEntry ->
            val bookId = navBackStackEntry.arguments?.getString(UiConstants.cocktailId)
            bookId?.let { CocktailDetailScreen(it, navController) }
        }
    }
}