package com.sapi.presentation.uicomponents

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.sapi.presentation.theme.Dimens

@Composable
internal fun ShowCocktailDetailMessages(text: String, textStyle: TextStyle) {

    Spacer(modifier = Modifier.height(Dimens.size8dp))
    Text(
        text = text,
        style = textStyle
    )
}