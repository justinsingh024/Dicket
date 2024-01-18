package com.example.dicket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.dicket.data.database.DicketDatabase
import com.example.dicket.ui.theme.DicketTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var database: DicketDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            // Set the status bar color and navigation bar color
            DicketTheme {
                SetStatusBarColor(
                    colorStatusBar = Color(0xFF242323),
                    colorNavigationBar = Color(0xFF242323)
                )
                DicketApp()
            }
        }
    }
}

// Preview function for the DicketApp composable
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DicketTheme {
        DicketApp()
    }
}

// Function to set the status bar and navigation bar color
@Composable
fun SetStatusBarColor(
    colorStatusBar: Color,         // @param colorStatusBar: Color for the status bar
    colorNavigationBar: Color      // @param colorNavigationBar: Color for the navigation bar
) {
    // Retrieve the system UI controller
    val systemUiController = rememberSystemUiController()

    // SideEffect to set the status bar and navigation bar colors
    SideEffect {
        systemUiController.setStatusBarColor(colorStatusBar)
        systemUiController.setNavigationBarColor(colorNavigationBar)
    }
}