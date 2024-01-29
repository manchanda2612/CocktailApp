package com.sapi.presentation.uicomponents

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.sapi.presentation.theme.Dimens

@Composable
internal fun ShowCocktailListMessage(
    text : String,
    textStyle : TextStyle,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center
) {



    Text(
        text = text,
        modifier = modifier,
        style = textStyle,
        textAlign = textAlign
    )
    Spacer(modifier = Modifier.height(Dimens.size10dp))

}