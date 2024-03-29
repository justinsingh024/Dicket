package com.example.dicket.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dicket.DicketScreen
import com.example.dicket.data.entity.Event
import com.example.dicket.data.entity.Location
import com.example.dicket.data.entity.User
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Composable function to display the details of an event and handle the purchase confirmation.
 *
 * @param modifier Modifier for styling the BuyScreen layout.
 * @param event Event object containing details about the event.
 * @param location Location object containing details about the event location.
 * @param user User object representing the current user.
 * @param navController Navigation controller for navigating between screens.
 */
@Composable
fun BuyScreen(
    modifier: Modifier = Modifier,
    event: Event,
    location: Location,
    user: User,
    navController: NavHostController?,
) {
    // State to manage the visibility of the purchase confirmation dialog
    var showDialog by remember { mutableStateOf(false) }

    // ViewModel initialization using Hilt
    val viewModel: OverviewViewModel = hiltViewModel()

    // Main Column layout for the BuyScreen
    Column {
        // Column for displaying event details
        Column(
            modifier = modifier
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
                .weight(4f)
        ) {
            // Display various event details using PaddedText composable
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            PaddedText(label = "Event:", value = event.title)
            PaddedText(label = "Price:", value = String.format("%.2f€", event.price))
            PaddedText(label = "Location:", value = location.locationName)
            PaddedText(label = "Entry: ", value = event.entry.toString() + " Uhr")
            PaddedText(label = "Date: ", value = event.date.format(formatter))
            PaddedText(label = "Available Tickets: ", value = event.maxQuantityTicket.toString())
        }

        // Button for initiating the purchase process
        Button(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.75f)
                .padding(12.dp),
            onClick = { showDialog = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(
                    255,
                    128,
                    54
                )
            ), // Change the color as needed
            shape = RoundedCornerShape(8.dp)

        ) {
            Text(text = "Pay: ${String.format("%.2f€", event.price)}", fontSize = 18.sp)
        }

        // AlertDialog for confirming the purchase
        if (showDialog) {
            AlertDialog(
                containerColor = Color(31, 41, 61),
                onDismissRequest = {
                    // Dismiss the AlertDialog
                    showDialog = false
                },
                title = {
                    Text(
                        text = "Purchase Confirmation",
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                },
                text = {
                    Text(
                        text = "Are you sure you want to proceed with " +
                                "the purchase of ${event.title} for ${
                                    String.format(
                                        "%.2f€",
                                        event.price
                                    )
                                }?",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                },
                confirmButton = {
                    // Confirm Button with logic for purchase confirmation
                    Button(
                        onClick = {
                            viewModel.buyTicket(user, event)
                            showDialog = false
                            navController?.navigate(DicketScreen.MyProfile.name)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(
                                255,
                                128,
                                54
                            )
                        ), // Change the color as needed
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    // Cancel Button for dismissing the confirmation
                    Button(
                        onClick = {
                            showDialog = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB23B3B)),
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

/**
 * Preview function for BuyScreen with example data.
 */
@Preview
@Composable
fun BuyScreen() {
    // Example data for an event, location, and user
    val exampleEvent = Event(
        eventID = 1,
        title = "Amazing Event",
        rating = 4.5f,
        description = "Join us for an incredible experience!",
        minAge = 18,
        entry = LocalTime.of(18, 30),
        date = LocalDate.of(2023, 4, 20),  // Set to 24 hours from now
        location = 1,
        image = "https://example.com/sample_image.jpg",
        category = 2,
        price = 49.99,
        latestCancelingDate = System.currentTimeMillis() - 86400000,  // Set to 24 hours ago
        maxQuantityTicket = 200,
        organizer = 1
    )
    val exampleLocation = Location(
        locationID = 2,
        street = "Langestraße",
        locationName = "Kunsthalle",
        houseNumber = "8",
        city = "Karlsruhe",
        plz = 76135
    )
    val exampleUser = User(
        userID = 1,
        prename = "Udo",
        surname = "Mustermann",
        email = "max@test.de",
        password = "passwort123",
        isVerified = false,
        discount = 5.0,
        birthdate = "1999-1-1",
        byteArrayOf()
    )
    // Call to BuyScreen with example data
    BuyScreen(
        event = exampleEvent,
        location = exampleLocation,
        user = exampleUser,
        navController = null
    )
}
