package com.sapi.presentation.screens.cocktaildetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sapi.presentation.model.cocktaildetail.CocktailDetailDisplay
import com.sapi.presentation.theme.Dimens
import com.sapi.presentation.uicomponents.DisplayCocktailImageFromUrl
import com.sapi.presentation.uicomponents.ShowCocktailDetail
import com.sapi.presentation.uicomponents.ShowErrorMessage
import com.sapi.presentation.uicomponents.ShowProgressBar


@Composable
internal fun CocktailDetailScreen(cocktailId: String) {

    with(hiltViewModel<CocktailDetailViewModel>()) {

        val viewState by viewState.collectAsState()

        when (viewState) {
            CocktailDetailViewState.Loading -> {
                ShowProgressBar(show = true)
            }

            is CocktailDetailViewState.Success -> {
                // Show Detail Screen
                ShowBookDetailScreen((viewState as CocktailDetailViewState.Success).data)
            }

            is CocktailDetailViewState.Failure -> {
                ShowProgressBar(show = false)
                ShowErrorMessage(message = (viewState as CocktailDetailViewState.Failure).error)
            }
        }
    }
}


@Composable
internal fun ShowBookDetailScreen(cocktailDetailModel: CocktailDetailDisplay) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(Dimens.size10dp)) {
            DisplayCocktailImageFromUrl(
                cocktailDetailModel.cocktailImage,
                Modifier
                    .fillMaxWidth()
                    .height(Dimens.size200dp)
            )
            ShowCocktailDetail(cocktailDetailModel)
        }
    }
}