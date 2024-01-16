package com.neeraj.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.neeraj.presentation.navgraph.NavigationGraph
import com.neeraj.presentation.theme.CocktailAppTheme
import dagger.hilt.android.AndroidEntryPoint


/**
 * @author Neeraj Manchanda
 * It is a main activity of the application that serves as the entry point for app.
 */
@AndroidEntryPoint
class CocktailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailAppTheme {
                        NavigationGraph()
            }
        }
    }
}

