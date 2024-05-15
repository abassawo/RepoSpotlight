package com.lindenlabs.repospotlight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.lindenlabs.repospotlight.navigation.AppNavigator
import com.lindenlabs.repospotlight.ui.theme.RepoSpotlightTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepoSpotlightTheme {
                // A surface container using the 'background' color from the theme
                val appNavigator = AppNavigator(rememberNavController())

                val viewModel: MainViewModel = hiltViewModel()
                LaunchedEffect(Unit) {
                    viewModel.fetchTopRepos()
                }
                RepoSpotlightTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        App(appNavigator)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RepoSpotlightTheme {

    }
}