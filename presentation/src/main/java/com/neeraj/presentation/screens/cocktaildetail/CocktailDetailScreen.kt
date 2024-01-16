package com.neeraj.presentation.screens.cocktaildetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import coil.request.CachePolicy
import coil.size.Scale
import com.neeraj.presentation.R
import com.neeraj.presentation.model.cocktaildetail.CocktailDetailDisplayModel
import com.neeraj.presentation.theme.Dimens
import com.neeraj.presentation.uicomponents.DisplayCocktailImageFromUrl
import com.neeraj.presentation.uicomponents.ShowErrorMessage
import com.neeraj.presentation.uicomponents.ShowProgressBar
import com.neeraj.presentation.uicomponents.ShowToolbar


@Composable
fun CocktailDetailScreen(cocktailId: String) {

    val cocktailViewModel: CocktailDetailViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        cocktailViewModel.sendIntent(CocktailDetailViewIntent.FetchCocktailDetail(cocktailId))
    }

    val viewState by cocktailViewModel.viewState.collectAsState()

    when (viewState) {
        is CocktailDetailViewState.Loading -> {
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


@Composable
fun ShowBookDetailScreen(cocktailDetailModel: CocktailDetailDisplayModel) {


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(Dimens.ten_dp)) {
            DisplayCocktailImageFromUrl(
                cocktailDetailModel.cocktailImage,
                Modifier
                    .fillMaxWidth()
                    .height(Dimens.two_hundred_dp),
                R.drawable.ic_placeholder,
                R.drawable.ic_placeholder,
                Scale.FILL,
                ContentScale.Fit,
                stringResource(R.string.cocktail_image),
                CachePolicy.ENABLED
            )

            Spacer(modifier = Modifier.height(Dimens.eight_dp))

            Text(
                text = cocktailDetailModel.cocktailName,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(Dimens.four_dp))

            Text(
                text = cocktailDetailModel.isAlcoholic,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(Dimens.four_dp))

            Text(
                text = cocktailDetailModel.cocktailCategory,
                style = MaterialTheme.typography.bodyMedium,
            )


            Spacer(modifier = Modifier.width(Dimens.four_dp))
            Text(
                text = cocktailDetailModel.cocktailInstructions,
                style = MaterialTheme.typography.bodyMedium,
            )


            Spacer(modifier = Modifier.height(Dimens.four_dp))

            Text(
                text = cocktailDetailModel.cocktailModifiedDate,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}