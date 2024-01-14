package com.example.dicket.ui

import android.graphics.Paint.Align
import android.provider.CalendarContract.Events
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dicket.R
import com.example.dicket.model.Event
import com.example.dicket.service.MockService

@Composable
fun MyProfilScreen(
    modifier: Modifier = Modifier,
    myEvents: List<Event>,
    myTickets: List<Event>,
    onLoginPressed: () -> Unit,
){
    Column(modifier = modifier
        .padding(6.dp)
        .fillMaxWidth()) {
        LoginInfoScreen(modifier = modifier
            .weight(1f)
            .fillMaxWidth(),
            onLoginPressed)
        PaymentScreen(modifier = modifier.weight(2f))
        Text(text = "Your Events")
        Spacer(modifier = modifier.height(8.dp))
        EventsListingScreen(modifier = modifier.weight(3f), MockService.allEvents)
        Spacer(modifier = modifier.height(16.dp))
        Text(text = "Your Tickets")
        Spacer(modifier = modifier.height(8.dp))
        EventsListingScreen(modifier = modifier.weight(3f), MockService.allEvents)

    }
}
@Composable
fun LoginInfoScreen(
    modifier: Modifier = Modifier,
    onLoginPressed: () -> Unit,
){
    Text(text = "My Login")
    Text(text = "Username", modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontStyle = FontStyle.Italic, fontSize = 18.sp)
    Row(modifier = modifier) {
        Spacer(modifier = modifier.weight(3f))
        Button(modifier = modifier.weight(1f), onClick = onLoginPressed) {
            Text(text = "Login")
        }
        Spacer(modifier = modifier.weight(3f))

    }
}
@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
        Text(text = "Payment Method")
        Column(
            modifier = modifier.padding(top = 8.dp),
        ) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
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
    }
}

@Composable
fun EventsListingScreen(
    modifier: Modifier = Modifier, myEvents: List<Event>
) {
    LazyColumn(modifier = modifier){
        items(myEvents) {
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
                    )
                    Column(modifier = modifier
                        .weight(6f)
                        .align(Alignment.CenterVertically)
                        .padding(8.dp)) {
                        Text(text = it.title)
                        Text(text = "${it.date} ${it.entry}")
                        Text(text = "${it.location}")
                    }

                }

            }
            Spacer(modifier = modifier.height(4.dp))
        }
    }
}

@Composable
fun EventListing(
    modifier: Modifier = Modifier
) {

}


@Composable
@Preview
fun MyProfileScreenPreview(){
    MyProfilScreen(modifier = Modifier, listOf<Event>(), listOf<Event>(), {})
}
