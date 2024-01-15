package com.example.dicket.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dicket.R
import com.example.dicket.data.entity.Event
import com.example.dicket.data.entity.User

@Composable
fun MyProfilScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    currentUser: User?,
    isLoggedIn: Boolean,
    modifier: Modifier = Modifier,
    myEvents: List<Event>?,
    myTickets: List<Event>?,
    onLoginPressed: () -> Unit,
    onLogoutPressed: () -> Unit,
) {
    //val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .padding(6.dp)
            .fillMaxWidth()
    ) {
        Log.d("MyProfileScreen", "isLoggedIn: $isLoggedIn")

        LoginInfoScreen(
            currentUser,
            isLoggedIn,
            modifier = modifier
                .weight(1f)
                .fillMaxWidth(),
            onLoginPressed,
            onLogoutPressed,
        )

        if (isLoggedIn) {
            PaymentScreen(modifier = modifier.weight(2f))
            Text(text = if (myEvents.isNullOrEmpty()) "You have no Events" else "Your Events")


            Spacer(modifier = modifier.height(8.dp))
            EventsListingScreen(modifier = modifier.weight(3f), myEvents, viewModel)
            Spacer(modifier = modifier.height(16.dp))
            Text(text = if (myTickets.isNullOrEmpty()) "You have no Tickets" else "Your Tickets")
            Spacer(modifier = modifier.height(8.dp))
            EventsListingScreen(modifier = modifier.weight(3f), myTickets, viewModel)
        }
    }
}

@Composable
fun LoginInfoScreen(
    currentUser: User?,
    isLoggedIn: Boolean,
    modifier: Modifier = Modifier,
    onLoginPressed: () -> Unit,
    onLogoutPressed: () -> Unit,
) {
    //Text(text = "My Login")
    if (isLoggedIn) {
        val prename = currentUser?.prename
        val surname = currentUser?.surname
        Text(
            text = "Welcome $prename $surname",
            modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp
        )
    }


    Row(modifier = modifier) {
        Spacer(modifier = modifier.weight(3f))

        if (isLoggedIn) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = modifier.weight(1f), onClick = onLogoutPressed
            ) {
                Text(text = "Logout")
            }
        } else {
            Button(modifier = modifier.weight(1f), onClick = onLoginPressed) {
                Text(text = "Login")
            }
        }

        Spacer(modifier = modifier.weight(3f))

    }
}

@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier
) {
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
                Row(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        modifier = modifier.weight(1f)
                    )
                    //Spacer(modifier = modifier.width(16.dp))
                    Text(text = "4544 **** **** 5615", modifier.weight(5f))
                    Text(text = "06/25", modifier.weight(1f))

                }

            }
        }
    }
}

@Composable
fun EventsListingScreen(
    modifier: Modifier = Modifier,
    myEventsMaybeNull: List<Event>?,
    viewModel: OverviewViewModel = hiltViewModel(),
) {
    var myEvents: List<Event>? = myEventsMaybeNull
    if (myEvents == null) {
        myEvents = emptyList()
    }
    LazyColumn(modifier = modifier) {
        items(myEvents) { event ->
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
            ) {
                Row(
                    modifier = modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.example_party),
                        contentDescription = "Party",
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        modifier = modifier.weight(3f)
                    )
                    Column(
                        modifier = modifier
                            .weight(6f)
                            .align(Alignment.CenterVertically)
                            .padding(8.dp)
                    ) {
                        Text(text = event.title)
                        Text(text = "${event.date} ${event.entry}")
                        viewModel.getLocationByEvent(event)?.let { Text(text = it.locationName) }
                    }

                }

            }
            Spacer(modifier = modifier.height(4.dp))
        }
    }
}

/*
@Composable
@Preview
fun MyProfileScreenPreview() {
    MyProfilScreen(isLoggedIn = false, modifier = Modifier, myEvents = listOf<Event>(), myTickets = listOf<Event>(), onLoginPressed = {})
}
*/
