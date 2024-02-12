package com.sapi.presentation.screens.cocktaillist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.sapi.presentation.model.cocktaillist.CocktailListDisplay
import com.sapi.presentation.uicomponents.CocktailItemCardView
import com.sapi.presentation.uicomponents.ShowErrorMessage
import com.sapi.presentation.uicomponents.ShowProgressBar


@Composable
internal fun CocktailListScreen(itemClick: (cocktailItem : String) -> Unit) {

    val cocktailViewModel = hiltViewModel<CocktailListViewModel>()

    with(cocktailViewModel) {

        val viewState by viewState.collectAsState()

        when (viewState) {

            CocktailListViewState.Loading -> {
                ShowProgressBar(show = true)
            }

            is CocktailListViewState.Success -> {
                ShowProgressBar(show = false)
                ShowCocktailList(
                    cocktailList = (viewState as CocktailListViewState.Success).data,
                    onItemClick = {
                        cocktailViewModel.sendIntent(
                            CocktailListViewIntent.OnViewCocktailItemClick(it)
                        )
                    })
            }

            is CocktailListViewState.Error -> {
                ShowProgressBar(show = false)
                ShowErrorMessage(message = (viewState as CocktailListViewState.Error).error)
            }
        }

        val sideEffect by sideEffects.collectAsState(0)
        when (sideEffect) {
            is CocktailListSideEffect.NavigateToDetails -> {
                LaunchedEffect(Unit) {
                    itemClick((sideEffect as CocktailListSideEffect.NavigateToDetails).id)
                }
            }
        }
    }
}

@Composable
internal fun ShowCocktailList(
    cocktailList: List<CocktailListDisplay>,
    onItemClick: (cocktailId : String) -> Unit
) {

    cocktailList.let {
        LazyColumn {
            items(it) {
                CocktailItemCardView(cocktail = it, onItemClick = onItemClick)
            }
        }
    }
}


