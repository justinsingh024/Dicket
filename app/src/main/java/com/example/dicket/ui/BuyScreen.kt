package com.example.dicket.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dicket.data.entity.Event
import com.example.dicket.data.entity.Location
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun BuyScreen(modifier: Modifier = Modifier, event: Event, location: Location) {
    Column {
        Column(
            modifier = modifier
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
                .weight(4f)
        ) {
            PaddedText(label = "Event:", value = event.title)
            PaddedText(label = "Price:", value = "${event.price}€")
            PaddedText(label = "Location:", value = location.locationName)
            PaddedText(label = "Entry: ", value = event.entry.toString())
            PaddedText(label = "Date: ", value = event.date.toString())
            PaddedText(label = "Available Tickets: ", value = event.maxQuantityTicket.toString())
        }
        Button(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.75f)
                .padding(12.dp),
            onClick = { /*TODO*/ },

            ) {
            Text(text = "Bezahlen: ${event.price}€")
        }
    }
}

@Preview
@Composable
fun BuyScreen() {
    val exampleEvent = Event(
        eventID = 1,
        title = "Amazing Event",
        rating = 4.5f,
        description = "Join us for an incredible experience!",
        minAge = 18,
        entry = LocalTime.of(18, 30),
        date = LocalDate.of(2023, 4, 20),  // Set to 24 hours from now
        //location = "Fantastic Venue",
        location = 1,
        image = "https://example.com/sample_image.jpg",
        //category = "Entertainment",
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
    BuyScreen(event = exampleEvent, location = exampleLocation)
}