package com.sapi.presentation.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sapi.presentation.R
import com.sapi.presentation.base.BaseScreen
import com.sapi.presentation.constant.PreviewConstant
import com.sapi.presentation.model.cocktaillist.CocktailListDisplay
import com.sapi.presentation.theme.Dimens

@Composable
internal fun CocktailItemCardView (cocktail: CocktailListDisplay, onItemClick: (cocktailId : String) -> Unit) {

    Column(modifier = Modifier.padding(Dimens.size5dp)) {
        Card(
            onClick = {
                onItemClick.invoke(cocktail.cocktailId)
            },
            modifier = Modifier
                .padding(Dimens.size10dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(Dimens.size10dp)) {
                DisplayCocktailImageFromUrl(
                    cocktail.cocktailImage,
                    Modifier
                        .fillMaxWidth()
                        .height(Dimens.size100dp)
                        .padding(Dimens.size4dp)
                )

                ShowCocktailListMessage(
                    text = cocktail.cocktailName,
                    textStyle = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.fillMaxWidth()
                )

                ShowCocktailListMessage(
                    text = cocktail.isAlcoholic,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CocktailItemCardViewPreview() {
    MaterialTheme {
        BaseScreen(title = stringResource(id = R.string.cocktail_listing)) {
            val cocktailListDisplay = CocktailListDisplay(PreviewConstant.CocktailId,PreviewConstant.CocktailName, PreviewConstant.IsCocktailAlcoholic,
                PreviewConstant.CocktailImage)
            CocktailItemCardView(cocktailListDisplay) {}
        }
    }
}
