package com.sapi.presentation.uicomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sapi.presentation.R
import com.sapi.presentation.base.BaseScreen

@Composable
internal fun ShowProgressBar(show : Boolean) {
    if (show) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun ShowProgressBarPreview() {
    MaterialTheme {
        BaseScreen(title = stringResource(id = R.string.cocktail_listing)) {
            ShowProgressBar(show = true)
        }
    }
}
