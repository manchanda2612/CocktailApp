package com.neeraj.presentation.screens.cocktaillist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import coil.request.CachePolicy
import coil.size.Scale
import com.neeraj.presentation.R
import com.neeraj.presentation.model.cocktaillist.CocktailListDisplayModel
import com.neeraj.presentation.uicomponents.DisplayCocktailImageFromUrl
import com.neeraj.presentation.theme.Dimens
import com.neeraj.presentation.uicomponents.ShowErrorMessage
import com.neeraj.presentation.uicomponents.ShowProgressBar


@Composable
fun CocktailListScreen(itemClick: (CocktailListDisplayModel) -> Unit) {

    val cocktailViewModel = hiltViewModel<CocktailListViewModel>()


    with(cocktailViewModel) {

        GetCocktailList(cocktailListViewModel = this)

        val viewState by cocktailViewModel.viewState.collectAsState()

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
fun ShowCocktailList(
    cocktailList: List<CocktailListDisplayModel>,
    onItemClick: (CocktailListDisplayModel) -> Unit
) {

    cocktailList.let {
        LazyColumn {
            items(it) {
                ItemCard(cocktail = it, onItemClick = onItemClick)
            }
        }
    }
}

@Composable
fun ItemCard(cocktail: CocktailListDisplayModel, onItemClick: (CocktailListDisplayModel) -> Unit) {

    Column(modifier = Modifier.padding(Dimens.five_dp)) {
        Card(
            onClick = {
                onItemClick.invoke(cocktail)
            },
            modifier = Modifier
                .padding(Dimens.ten_dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(Dimens.ten_dp)) {
                DisplayCocktailImageFromUrl(
                    cocktail.cocktailImage,
                    Modifier
                        .fillMaxWidth()
                        .height(Dimens.hundred_dp)
                        .padding(Dimens.four_dp),
                    R.drawable.ic_placeholder,
                    R.drawable.ic_placeholder,
                    Scale.FILL,
                    ContentScale.Fit,
                    stringResource(R.string.cocktail_image),
                    CachePolicy.ENABLED
                )
                Text(
                    text = cocktail.cocktailName,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(Dimens.ten_dp))
                if (cocktail.isAlcoholic.isNotEmpty()) {
                    Text(
                        text = cocktail.isAlcoholic,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(Dimens.ten_dp))
                }
            }
        }
    }
}


