package com.example.dicket

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dicket.ui.OverviewViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dicket.ui.OverviewScreen


enum class DicketScreen() {
    Overview,
}


@Composable
fun DicketApp(
    viewModel: OverviewViewModel = viewModel(),
    navController: NavHostController = rememberNavController()

) {
    Scaffold {
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
        }
    }
}