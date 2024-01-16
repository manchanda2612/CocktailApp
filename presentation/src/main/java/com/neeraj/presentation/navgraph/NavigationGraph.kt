package com.neeraj.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neeraj.presentation.R
import com.neeraj.presentation.base.BaseScreen
import com.neeraj.presentation.constant.UiConstants
import com.neeraj.presentation.screens.cocktaillist.CocktailListScreen
import com.neeraj.presentation.route.Routes
import com.neeraj.presentation.screens.cocktaildetail.CocktailDetailScreen


/**
 * @author Neeraj Manchanda
 * This function define a navigation graph using Navigation component.
 * It sets up navigation between two destinations: a cocktail list screen and a cocktail detail screen.
 */
@Composable
fun NavigationGraph() {

    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Routes.CocktailListScreen.route) {

        // Handle Listing screen here
        composable(Routes.CocktailListScreen.route) {
            BaseScreen(title = stringResource(id = R.string.cocktail_listing)) {
                CocktailListScreen(
                    itemClick = { cocktailListDisplayModel ->
                        navController.navigate(Routes.CocktailDetailScreen.route + "/${cocktailListDisplayModel.cocktailId}")
                    }
                )
            }
        }

        // Handle Detail screen here
        composable(Routes.CocktailDetailScreen.route + "/{cocktailId}") { navBackStackEntry ->
            val cocktailId = navBackStackEntry.arguments?.getString(UiConstants.cocktailId)
            cocktailId?.let {
                BaseScreen(title = stringResource(id = R.string.cocktail_detail),
                    showBackButton = true,
                    onBackButtonClicked = {
                        navController.popBackStack()
                    }) {
                    CocktailDetailScreen(cocktailId = cocktailId)
                }
            }
        }
    }
}