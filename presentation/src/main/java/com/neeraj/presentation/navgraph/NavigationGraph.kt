package com.neeraj.presentation.navgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neeraj.presentation.R
import com.neeraj.presentation.constant.UiConstants
import com.neeraj.presentation.screens.cocktaillist.CocktailListScreen
import com.neeraj.presentation.route.Routes
import com.neeraj.presentation.screens.cocktaildetail.CocktailDetailScreen
import com.neeraj.presentation.uicomponents.ShowToolbar


/**
 * @author Neeraj Manchanda
 * This function define a navigation graph using Navigation component.
 * It sets up navigation between two destinations: a cocktail list screen and a cocktail detail screen.
 */
@Composable
fun NavigationGraph() {

    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Routes.CocktailListScreen.route) {

        composable(Routes.CocktailListScreen.route) {
            CocktailListScreen(
                itemClick = { cocktailListDisplayModel ->
                    navController.navigate(Routes.CocktailDetailScreen.route + "/${cocktailListDisplayModel.cocktailId}")
                }
            )
        }

        // Handle Detail screen here
        composable(Routes.CocktailDetailScreen.route + "/{cocktailId}") { navBackStackEntry ->
            val cocktailId = navBackStackEntry.arguments?.getString(UiConstants.cocktailId)
            cocktailId?.let {
                Scaffold(
                    topBar = {
                        ShowToolbar(stringResource(R.string.cocktail_Detail), true) { navController.popBackStack() }
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = it.calculateTopPadding()),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        CocktailDetailScreen(cocktailId)
                    }
                }
            }
        }
    }
}