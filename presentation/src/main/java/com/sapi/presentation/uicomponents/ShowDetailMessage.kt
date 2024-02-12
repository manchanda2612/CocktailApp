package com.sapi.presentation.uicomponents

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.sapi.presentation.R
import com.sapi.presentation.base.BaseScreen
import com.sapi.presentation.constant.PreviewConstant
import com.sapi.presentation.theme.Dimens

@Composable
internal fun ShowCocktailDetailMessages(text: String, textStyle: TextStyle) {

    Spacer(modifier = Modifier.height(Dimens.size8dp))
    Text(
        text = text,
        style = textStyle
    )
}
@Preview(showBackground = true)
@Composable
private fun ShowCocktailDetailMessagesPreview() {
    MaterialTheme {
        BaseScreen(title = stringResource(id = R.string.cocktail_listing)) {
            ShowCocktailDetailMessages(PreviewConstant.CocktailName, MaterialTheme.typography.headlineMedium)
        }
    }
}
