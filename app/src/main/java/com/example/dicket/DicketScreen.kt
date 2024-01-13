package com.example.dicket

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dicket.ui.OverviewViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dicket.model.Event
import com.example.dicket.ui.BuyScreen
import com.example.dicket.ui.DetailScreen
import com.example.dicket.ui.OverviewScreen
import java.time.LocalDate
import java.time.LocalTime

private const val TAG = "DicketScreen"

enum class DicketScreen() {
    Overview,
    Detail,
    Buy,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DicketAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Composable
fun DicketApp(
    viewModel: OverviewViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()

) {
    Scaffold(
        topBar = {
            DicketAppBar(
                canNavigateBack = true,
                navigateUp = {
                    navController.navigateUp()
                },
            )
        }
    ) {
        innerPadding ->
        //val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = DicketScreen.Overview.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route =  DicketScreen.Overview.name) {
                OverviewScreen()
            }
            composable(route =  DicketScreen.Detail.name) {
                val exampleEvent = Event(
                    title = "Amazing Event",
                    rating = 3.5f,
                    description = "Join us for an incredible experience!",
                    minAge = 18,
                    entry = LocalTime.of(18,30),
                    date = LocalDate.of(2023, 4, 20),  // Set to 24 hours from now
                    location = "Fantastic Venue",
                    image = "https://example.com/sample_image.jpg",
                    category = "Entertainment",
                    price = 49.99,
                    latestCancelingDate = System.currentTimeMillis() - 86400000,  // Set to 24 hours ago
                    maxQuantityTicket = 200
                )
                DetailScreen(event = exampleEvent, onBuyPressed = {
                    Log.d(TAG, "Buy has been clicked")
                    navController.navigate(DicketScreen.Buy.name)
                })
            }
            composable(route =  DicketScreen.Buy.name) {
                val exampleEvent = Event(
                    title = "Amazing Event",
                    rating = 3.5f,
                    description = "Join us for an incredible experience!",
                    minAge = 18,
                    entry = LocalTime.of(18,30),
                    date = LocalDate.of(2023, 4, 20),  // Set to 24 hours from now
                    location = "Fantastic Venue",
                    image = "https://example.com/sample_image.jpg",
                    category = "Entertainment",
                    price = 49.99,
                    latestCancelingDate = System.currentTimeMillis() - 86400000,  // Set to 24 hours ago
                    maxQuantityTicket = 200
                )
                BuyScreen(event = exampleEvent)
            }
        }
    }
}
