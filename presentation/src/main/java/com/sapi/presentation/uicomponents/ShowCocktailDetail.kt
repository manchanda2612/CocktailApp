package com.sapi.presentation.uicomponents

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.sapi.presentation.model.cocktaildetail.CocktailDetailDisplay
@Composable
internal fun ShowCocktailDetail (cocktailDetailModel: CocktailDetailDisplay) {

    ShowCocktailDetailMessages(
        text = cocktailDetailModel.cocktailName,
        textStyle = MaterialTheme.typography.headlineLarge
    )

    ShowCocktailDetailMessages(
        text = cocktailDetailModel.isAlcoholic,
        textStyle = MaterialTheme.typography.headlineSmall
    )

    ShowCocktailDetailMessages(
        text = cocktailDetailModel.cocktailCategory,
        textStyle = MaterialTheme.typography.bodyMedium
    )

    ShowCocktailDetailMessages(
        text = cocktailDetailModel.cocktailInstruction,
        textStyle = MaterialTheme.typography.bodyMedium
    )

    ShowCocktailDetailMessages(
        text = cocktailDetailModel.cocktailModifiedDate,
        textStyle = MaterialTheme.typography.bodyMedium
    )

}