package com.neeraj.presentation.screens

import androidx.compose.runtime.Composable

import androidx.navigation.NavController


/**
 * @author Neeraj Manchanda
 * It is a Composable function that represents the UI for displaying cocktail details using Jetpack Compose.
 */
@Composable
fun CocktailDetailScreen(cocktailId : String, navController: NavController) {

  /*  val viewModel: cocktailDetailViewModel = hiltViewModel()
    val cocktailDetailInfo = viewModel.cocktailDetail.collectAsState()
*/
    /*LaunchedEffect(Unit) {
        viewModel.getcocktailDetail(cocktailId)
    }*/

    /*Column(
        modifier = Modifier.fillMaxWidth()) {
        ShowToolbar(stringResource(R.string.cocktail_info), true) { navController.popBackStack() }
        when (cocktailDetailInfo.value) {
            is Resources.Loading -> ShowProgressBar()
            is Resources.Success -> ShowcocktailDetailScreen((cocktailDetailInfo.value as Resources.Success<cocktailDetailModel>).data)
            is Resources.Failure -> ShowErrorMessage(cocktailDetailInfo.value.toString())
        }
    }*/
}

/*@Composable
fun ShowcocktailDetailScreen(cocktailDetailModel: cocktailDetailModel) {

        Column(modifier = Modifier.padding(Dimens.ten_dp)) {
            DisplaycocktailImageFromUrl(
                cocktailDetailModel.thumbnail,
                Modifier.fillMaxWidth().height(Dimens.two_hundred_dp),
                R.drawable.ic_cocktail_placeholder,
                R.drawable.ic_cocktail_placeholder,
                Scale.FILL,
                ContentScale.Fit,
                stringResource(R.string.cocktail_image),
                CachePolicy.ENABLED
            )

            Spacer(modifier = Modifier.height(Dimens.eight_dp))

            Text(
                text = cocktailDetailModel.cocktailTitle,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(Dimens.four_dp))

            Text(
                text = cocktailDetailModel.cocktailSubtitle,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(Dimens.four_dp))

            Text(
                text = stringResource(R.string.written_by, cocktailDetailModel.cocktailAuthors),
                style = MaterialTheme.typography.bodyMedium
            )


            Spacer(modifier = Modifier.width(Dimens.four_dp))
            Text(
                text = stringResource(R.string.published_by, cocktailDetailModel.publisher),
                style = MaterialTheme.typography.bodyMedium,
            )


            Spacer(modifier = Modifier.height(Dimens.four_dp))

            Text(
                text = stringResource(R.string.published_on, cocktailDetailModel.publishDate),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }*/
