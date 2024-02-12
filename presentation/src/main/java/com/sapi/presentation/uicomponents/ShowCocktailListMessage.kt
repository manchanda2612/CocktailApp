package com.sapi.presentation.uicomponents

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.sapi.presentation.R
import com.sapi.presentation.base.BaseScreen
import com.sapi.presentation.constant.PreviewConstant
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

@Preview(showBackground = true)
@Composable
private fun ShowCocktailListMessagePreview() {
    MaterialTheme {
        BaseScreen(title = stringResource(id = R.string.cocktail_listing)) {
            ShowCocktailListMessage(
                text = PreviewConstant.CocktailName,
                textStyle = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}