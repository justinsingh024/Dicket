package com.example.dicket.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Define dark and light color schemes
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,       // Define the primary color for dark mode
    secondary = PurpleGrey80, // Define the secondary color for dark mode
    tertiary = Pink80         // Define the tertiary color for dark mode
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,       // Define the primary color for light mode
    secondary = PurpleGrey40, // Define the secondary color for light mode
    tertiary = Pink40         // Define the tertiary color for light mode
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun DicketTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Determine the color scheme based on dynamic color and Android version
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme  // Use dark color scheme if not using dynamic color or on older Android versions
        else -> LightColorScheme      // Use light color scheme if not using dynamic color or on older Android versions
    }

    // Apply color changes to the status bar and system UI
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor =
                colorScheme.primary.toArgb() // Set the status bar color based on the primary color
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    // Apply the selected color scheme and typography to the MaterialTheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
