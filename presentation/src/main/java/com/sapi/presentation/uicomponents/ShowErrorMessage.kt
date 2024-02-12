package com.sapi.presentation.uicomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sapi.common.constant.CommonConstant
import com.sapi.presentation.R
import com.sapi.presentation.base.BaseScreen

@Composable
internal fun ShowErrorMessage(message : String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message)
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowErrorMessagePreview() {
    MaterialTheme {
        BaseScreen(title = stringResource(id = R.string.cocktail_listing)) {
            ShowErrorMessage(CommonConstant.InternetErrorMessage)
        }
    }
}
