package com.sapi.presentation.screens.cocktaillist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.sapi.presentation.model.cocktaillist.CocktailListDisplay
import com.sapi.presentation.uicomponents.CocktailItemCardView
import com.sapi.presentation.uicomponents.ShowErrorMessage
import com.sapi.presentation.uicomponents.ShowProgressBar


@Composable
internal fun CocktailListScreen(itemClick: (CocktailListDisplay) -> Unit) {

    with(hiltViewModel<CocktailListViewModel>()) {

        GetCocktailList(cocktailListViewModel = this)

        val viewState by viewState.collectAsState()

        when (viewState) {

            is CocktailListViewState.Loading -> {
                ShowProgressBar(show = true)
            }

            is CocktailListViewState.Success -> {
                ShowProgressBar(show = false)
                ShowCocktailList(cocktailList = (viewState as CocktailListViewState.Success).data,
                    onItemClick = {
                        itemClick.invoke(it)
                    })
            }

            is CocktailListViewState.Error -> {
                ShowProgressBar(show = false)
                ShowErrorMessage(message = (viewState as CocktailListViewState.Error).error)
            }

        }
    }
}

@Composable
private fun GetCocktailList(cocktailListViewModel: CocktailListViewModel) {

    var initialApiCalled by rememberSaveable { mutableStateOf(false) }

    if (!initialApiCalled) {
        LaunchedEffect(Unit) {
            cocktailListViewModel.sendIntent(CocktailListViewIntent.FetchCocktailListView)
            initialApiCalled = true
        }
    }
}

@Composable
private fun ShowCocktailList(
    cocktailList: List<CocktailListDisplay>,
    onItemClick: (CocktailListDisplay) -> Unit
) {

    cocktailList.let {
        LazyColumn {
            items(it) {
                CocktailItemCardView(cocktail = it, onItemClick = onItemClick)
            }
        }
    }
}


