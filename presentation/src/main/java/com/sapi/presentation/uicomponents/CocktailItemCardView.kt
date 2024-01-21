package com.sapi.presentation.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sapi.presentation.model.cocktaillist.CocktailListDisplay
import com.sapi.presentation.theme.Dimens

@Composable
internal fun CocktailItemCardView (cocktail: CocktailListDisplay, onItemClick: (CocktailListDisplay) -> Unit) {

    Column(modifier = Modifier.padding(Dimens.size5dp)) {
        Card(
            onClick = {
                onItemClick.invoke(cocktail)
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
                    textStyle = MaterialTheme.typography.headlineMedium
                )

                ShowCocktailListMessage(
                    text = cocktail.isAlcoholic,
                    textStyle = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}