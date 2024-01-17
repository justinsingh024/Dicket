package com.example.dicket

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dicket.ui.BuyScreen
import com.example.dicket.ui.DetailScreen
import com.example.dicket.ui.LoginScreen
import com.example.dicket.ui.MyProfilScreen
import com.example.dicket.ui.NewEventScreen
import com.example.dicket.ui.OverviewScreen
import com.example.dicket.ui.OverviewViewModel

//private const val TAG = "DicketScreen"

enum class DicketScreen {
    Overview,
    Detail,
    Buy,
    MyProfile,
    Login,
    NewEvent,
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
                containerColor = Color(0xFF242323)
                //containerColor = Color(255, 128, 54)
                //backgroundColor = Color(255, 128, 54)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                // Linkes Element
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = null,
                            tint = if (currentDestination?.hierarchy?.any { it.route == DicketScreen.Overview.name } == true) {
                                Color(0xFF242323) // Ausgewählte Farbe
                            } else {
                                Color(255, 128, 54) // Nicht ausgewählte Farbe
                            }
                        )
                    },
                    label = { Text(DicketScreen.Overview.name, color = Color(255, 128, 54)) },
                    selected = currentDestination?.hierarchy?.any { it.route == DicketScreen.Overview.name } == true,
                    onClick = {
                        navController.navigate(DicketScreen.Overview.name) {
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(
                            255,
                            128,
                            54
                        )
                    )
                )

                // Platzhalter für das mittlere Element

                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = null,
                            tint = if (currentDestination?.hierarchy?.any { it.route == DicketScreen.NewEvent.name } == true) {
                                Color(0xFF242323) // Ausgewählte Farbe
                            } else {
                                Color(255, 128, 54) // Nicht ausgewählte Farbe
                            }
                        )
                    },
                    label = { Text(DicketScreen.NewEvent.name, color = Color(255, 128, 54)) },
                    selected = currentDestination?.hierarchy?.any { it.route == DicketScreen.NewEvent.name } == true,
                    onClick = {
                        navController.navigate(DicketScreen.NewEvent.name) {
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(
                            255,
                            128,
                            54
                        )
                    )
                )
                // Rechtes Element
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = null,
                            tint = if (currentDestination?.hierarchy?.any { it.route == DicketScreen.MyProfile.name } == true) {
                                Color(0xFF242323) // Ausgewählte Farbe
                            } else {
                                Color(255, 128, 54) // Nicht ausgewählte Farbe
                            }
                        )
                    },
                    label = { Text(DicketScreen.MyProfile.name, color = Color(255, 128, 54)) },
                    selected = currentDestination?.hierarchy?.any { it.route == DicketScreen.MyProfile.name } == true,
                    onClick = {
                        navController.navigate(DicketScreen.MyProfile.name) {
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(
                            255,
                            128,
                            54
                        )
                    )
                )
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
                if (uiState.isLoggedIn) {
                    BuyScreen(
                        event = uiState.clickedEvent ?: error("Clicked event is null"),
                        location = uiState.clickedEventLocation
                            ?: error("Clicked location is null"),
                        user = uiState.currentUser ?: error("Clicked user is null"),
                        navController = navController,
                    )
                } else {
                    ShowLoginScreen(viewModel, navController, DicketScreen.Buy.name)
                }

            }
            composable(route = DicketScreen.MyProfile.name) {
                if (uiState.isLoggedIn) {
                    MyProfilScreen(
                        currentUser = uiState.currentUser,
                        isLoggedIn = uiState.isLoggedIn,
                        myEvents = viewModel.getEventsByUserOrganizer(uiState.currentUser),
                        myTickets = viewModel.getEventsByUserTickets(uiState.currentUser),
                        onLoginPressed = {
                            navController.navigate(DicketScreen.Login.name)
                        },
                        onLogoutPressed = {
                            viewModel.logout()
                            navController.navigate(DicketScreen.MyProfile.name)
                        }
                    )
                } else {
                    ShowLoginScreen(viewModel, navController, DicketScreen.MyProfile.name)
                }

            }
            composable(route = DicketScreen.Login.name) {
                ShowLoginScreen(viewModel, navController, DicketScreen.MyProfile.name)
            }
            composable(route = DicketScreen.NewEvent.name) {
                if (uiState.isLoggedIn) {

                    NewEventScreen(
                        onCreateEvent = viewModel::createEvent
                    ) {
                        navController.navigate(DicketScreen.MyProfile.name)
                    }
                } else {
                    ShowLoginScreen(viewModel, navController, DicketScreen.NewEvent.name)
                }
            }
        }
    }
}

@Composable
fun ShowLoginScreen(
    viewModel: OverviewViewModel,
    navController: NavHostController,
    afterLoginScreen: String
) {
    var showDialog by remember { mutableStateOf(false) }

    LoginScreen(
        onLoginClicked = { mail, password ->
            val loginCheck = viewModel.login(mail, password)
            if (loginCheck) {
                navController.navigate(afterLoginScreen)
            } else {
                showDialog = true
            }
            loginCheck
        },
        onLoginFailed = {
            showDialog = true
        }
    )

    if (showDialog) {
        AlertDialog(
            containerColor = Color(0xFF1F293D),
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    "Login failed!",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            },
            text = {
                Text(
                    "Incorrect e-mail or password",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            },
            confirmButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(
                            255,
                            128,
                            54
                        )
                    ), // Change the color as needed
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text("Try agian")
                }
            }
        )
    }
}

