package com.sapi.presentation.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.sapi.presentation.R
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


/**
 * @author Neeraj Manchanda
 * A composable function representing the base screen structure.
 *
 * @param title The title of the screen displayed in the app bar.
 * @param showBackButton Whether to show the back button in the app bar.
 * @param onBackButtonClicked Callback to be invoked when the back button is clicked.
 * @param content The content to be displayed on the screen.
 */
@Composable
internal fun BaseScreen(
    title: String,
    showBackButton: Boolean = false,
    onBackButtonClicked: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(R.color.purple_50)),
                title = {
                    Text(
                        text = title,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = if (showBackButton) {
                    {
                        IconButton(onClick = { onBackButtonClicked?.invoke() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(R.string.back),
                                tint = Color.White
                            )
                        }
                    }
                } else {
                    {  }
                }
            )
        },
    ) {
        Surface(modifier = Modifier.padding(it)) {
            content()
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun BaseScreenPreview() {
    MaterialTheme {
        BaseScreen(title = stringResource(id = R.string.cocktail_listing)) {
             Text(text = stringResource(id = R.string.app_name))
        }
    }
}