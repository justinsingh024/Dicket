package com.example.dicket.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dicket.R
import com.example.dicket.model.Event
import com.example.dicket.service.MockService

@Composable
fun MyProfilScreen(
    modifier: Modifier = Modifier,
){
    Column(modifier = modifier
        .padding(6.dp)
        .fillMaxWidth()) {
        PaymentScreen(modifier = modifier)
        Spacer(modifier = modifier.height(25.dp))
    }
}

@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
        Text(text = "Payment Method")
        Spacer(modifier = modifier.height(8.dp))
        Column(
            modifier = modifier
        ) {
            Card(
                modifier = modifier.fillMaxWidth()
            ) {
                Row(modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth()) {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null, modifier = modifier.weight(1f))
                    //Spacer(modifier = modifier.width(16.dp))
                    Text(text = "4544 **** **** 5615",modifier.weight(5f))
                    Text(text = "06/25", modifier.weight(1f))

                }

            }
        }
        Spacer(modifier = modifier.height(16.dp))
        Column(
            modifier = modifier
        ) {
            Text(text = "Your Events")
            Spacer(modifier = modifier.height(8.dp))
            EventsListingScreen(modifier = modifier, MockService.allEvents)
            Spacer(modifier = modifier.height(16.dp))
            Text(text = "Your Tickets")
            Spacer(modifier = modifier.height(8.dp))
            EventsListingScreen(modifier = modifier, MockService.allEvents)
        }
    }
}

@Composable
fun EventsListingScreen(
    modifier: Modifier = Modifier, events: List<Event>
) {
    LazyColumn(modifier = modifier){
        item {
            EventListing(modifier = modifier)
        }
    }
}

@Composable
fun EventListing(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Row(modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.example_party),
                contentDescription = "Party",
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                modifier = modifier.weight(3f)
            )            //Spacer(modifier = modifier.width(16.dp))
            Column(modifier = modifier.weight(6f).align(Alignment.CenterVertically).padding(8.dp)) {
                Text(text = "Event 1")
                Text(text = "17.11.2023, 19 Uhr")
                Text(text = "Teststraße 11, 76666 Testsatdt")
            }

        }

    }
}


@Composable
@Preview
fun MyProfileScreenPreview(){
    MyProfilScreen()
}
