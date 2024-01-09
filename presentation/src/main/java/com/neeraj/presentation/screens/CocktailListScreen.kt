package com.neeraj.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import coil.request.CachePolicy
import coil.size.Scale
import com.neeraj.domain.model.CocktailListModel
import com.neeraj.presentation.R
import com.neeraj.presentation.uicomponents.DisplayBookImageFromUrl
import com.neeraj.presentation.uicomponents.ShowToolbar
import com.neeraj.presentation.route.Routes
import com.neeraj.presentation.theme.Dimens


/**
 * @author Neeraj Manchanda
 * It is a Composable function that represents the UI for displaying book list using Jetpack Compose.
 */
@Composable
fun CocktailListScreen(navController: NavController) {

   /* val bookViewModel: BookListViewModel = hiltViewModel()
    val bookList = bookViewModel.bookListViewModel.collectAsState()*/


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ShowToolbar(stringResource(R.string.cocktail_listing))
        /*when (bookList.value) {
            is Resources.Loading -> ShowProgressBar()
            is Resources.Success<*> -> ShowCocktailList((bookList.value as Resources.Success<List<CocktailListModel>>).data, navController)
            is Resources.Failure -> ShowErrorMessage(bookList.value.toString())
        }*/
    }
}


@Composable
fun ShowCocktailList(cocktailList: List<CocktailListModel>, navController: NavController) {

           /* cocktailList.let {
                LazyColumn {
                    items(it) {
                        ItemCard(it, navController)
                    }
                }
        }*/
}

@Composable
fun ItemCard(cocktail : CocktailListModel, navController: NavController) {

    Column(modifier = Modifier.padding(Dimens.five_dp)) {
        Card(
            modifier = Modifier
                .padding(Dimens.ten_dp)
                .clickable { onClick(cocktail, navController) }
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(Dimens.ten_dp)) {
                DisplayBookImageFromUrl(
                    cocktail.cocktailImage,
                    Modifier.fillMaxWidth().height(Dimens.hundred_dp)
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


private fun onClick(cocktail: CocktailListModel, navController: NavController) {
    navController.navigate(Routes.CocktailDetailScreen.route + "/${cocktail.cocktailId}")
}


