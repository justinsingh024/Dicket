package com.example.dicket.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dicket.R
import com.example.dicket.model.Event
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun BuyScreen(modifier: Modifier = Modifier, event: Event) {
    Column {
        Column(
            modifier = modifier
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
                .weight(4f)
        ) {
            PaddedText(label = "Event:", value = event.title)
            PaddedText(label = "Price:", value = "${event.price}€")
            PaddedText(label = "Location:", value = event.location)
            PaddedText(label = "Entry: ", value = event.entry.toString())
            PaddedText(label = "Date: ", value = event.date.toString())
            PaddedText(label = "Available Tickets: ", value = event.maxQuantityTicket.toString())
        }
        Button(
            modifier = modifier.fillMaxWidth().weight(0.75f).padding(12.dp),
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
        title = "Amazing Event",
        rating = 4.5f,
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