package com.neeraj.presentation.screens

import androidx.compose.runtime.Composable

import androidx.navigation.NavController


/**
 * @author Neeraj Manchanda
 * It is a Composable function that represents the UI for displaying cocktail details using Jetpack Compose.
 */
@Composable
fun CocktailDetailScreen(cocktailId : String, navController: NavController) {

  /*  val viewModel: BookDetailViewModel = hiltViewModel()
    val bookDetailInfo = viewModel.bookDetail.collectAsState()
*/
    /*LaunchedEffect(Unit) {
        viewModel.getBookDetail(bookId)
    }*/

    /*Column(
        modifier = Modifier.fillMaxWidth()) {
        ShowToolbar(stringResource(R.string.book_info), true) { navController.popBackStack() }
        when (bookDetailInfo.value) {
            is Resources.Loading -> ShowProgressBar()
            is Resources.Success -> ShowBookDetailScreen((bookDetailInfo.value as Resources.Success<BookDetailModel>).data)
            is Resources.Failure -> ShowErrorMessage(bookDetailInfo.value.toString())
        }
    }*/
}

/*@Composable
fun ShowBookDetailScreen(bookDetailModel: BookDetailModel) {

        Column(modifier = Modifier.padding(Dimens.ten_dp)) {
            DisplayBookImageFromUrl(
                bookDetailModel.thumbnail,
                Modifier.fillMaxWidth().height(Dimens.two_hundred_dp),
                R.drawable.ic_book_placeholder,
                R.drawable.ic_book_placeholder,
                Scale.FILL,
                ContentScale.Fit,
                stringResource(R.string.book_image),
                CachePolicy.ENABLED
            )

            Spacer(modifier = Modifier.height(Dimens.eight_dp))

            Text(
                text = bookDetailModel.bookTitle,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(Dimens.four_dp))

            Text(
                text = bookDetailModel.bookSubtitle,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(Dimens.four_dp))

            Text(
                text = stringResource(R.string.written_by, bookDetailModel.bookAuthors),
                style = MaterialTheme.typography.bodyMedium
            )


            Spacer(modifier = Modifier.width(Dimens.four_dp))
            Text(
                text = stringResource(R.string.published_by, bookDetailModel.publisher),
                style = MaterialTheme.typography.bodyMedium,
            )


            Spacer(modifier = Modifier.height(Dimens.four_dp))

            Text(
                text = stringResource(R.string.published_on, bookDetailModel.publishDate),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }*/
