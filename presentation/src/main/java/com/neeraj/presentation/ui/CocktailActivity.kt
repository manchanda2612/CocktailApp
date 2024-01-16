package com.neeraj.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.neeraj.presentation.R
import com.neeraj.presentation.navgraph.NavigationGraph
import com.neeraj.presentation.theme.CocktailAppTheme
import com.neeraj.presentation.uicomponents.ShowToolbar
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

                // A surface container using the 'background' color from the theme

                        NavigationGraph()
            }
        }
    }
}

