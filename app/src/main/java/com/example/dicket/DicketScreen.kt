package com.example.dicket

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dicket.service.MockService
import com.example.dicket.ui.BuyScreen
import com.example.dicket.ui.DetailScreen
import com.example.dicket.ui.LoginScreen
import com.example.dicket.ui.MyProfilScreen
import com.example.dicket.ui.OverviewScreen
import com.example.dicket.ui.OverviewViewModel

//private const val TAG = "DicketScreen"

enum class DicketScreen() {
    Overview,
    Detail,
    Buy,
    MyProfile,
    Login,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DicketAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name), color = Color.White) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0xFF242323)
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
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
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            DicketAppBar(
                canNavigateBack = true,
                navigateUp = {
                    navController.navigateUp()
                },
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(255, 128, 54)
            //backgroundColor = Color(255, 128, 54)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                DicketScreen.entries.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(screen.toString()) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.toString() } == true,
                        onClick = {
                            navController.navigate(screen.toString()) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        },
        containerColor = Color(0xFF111620)

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DicketScreen.Overview.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = DicketScreen.Overview.name) {
                OverviewScreen(
                    onOpenDetail = {
                        viewModel.setClickedEvent(it)
                        Log.d("DicketScreen", "EventCategory: ${it.eventID}")
                        navController.navigate(DicketScreen.Detail.name)
                    }
                )
            }
            composable(route = DicketScreen.Detail.name) {
                DetailScreen(
                    event = uiState.clickedEvent ?: error("Clicked event is null"),
                    categorie = uiState.clickedEventCategory ?: error("Clicked category is null"),
                    location = uiState.clickedEventLocation ?: error("Clicked location is null"),
                    onBuyPressed = {
                    navController.navigate(DicketScreen.Buy.name)
                })
            }
            composable(route = DicketScreen.Buy.name) {
                BuyScreen(
                    event = uiState.clickedEvent ?: error("Clicked event is null"),
                    location = uiState.clickedEventLocation ?: error("Clicked location is null"),
                    )
            }
            composable(route = DicketScreen.MyProfile.name) {
                MyProfilScreen(
                    currentUser = uiState.currentUser,
                    isLoggedIn = uiState.isLoggedIn,
                    myEvents = MockService.allEvents,
                    myTickets = MockService.allEvents,
                    onLoginPressed = {
                        navController.navigate(DicketScreen.Login.name)
                    },
                    onLogoutPressed = {
                        viewModel.logout()
                        navController.navigate(DicketScreen.MyProfile.name)
                    }
                )
            }
            composable(route = DicketScreen.Login.name) {
                LoginScreen(onLoginClicked = { mail, password ->
                    viewModel.login(mail, password)
                    navController.navigate(DicketScreen.MyProfile.name)
                })
            }
        }
    }
}

