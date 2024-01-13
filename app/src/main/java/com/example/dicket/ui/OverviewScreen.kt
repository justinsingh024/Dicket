package com.example.dicket.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dicket.R

@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    //onOpenDetail: (Event) -> Unit,
    modifier: Modifier = Modifier,
){
    val allEvents = viewModel.allEvents

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        modifier = modifier
    ) {
        items(allEvents.value) { event ->
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),

            ) {
            }
            Column() {
                Image(
                    painter = painterResource(id = R.drawable.example_party),
                    contentDescription = "Party",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                )
                Row(
                    modifier = modifier,

                ) {
                    Text(
                        text = event.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(6.dp)
                    )
                }
                Text(
                    text = event.date.toString(),
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp,
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Start,
                    modifier = modifier.padding(2.dp)
                )
            }

            }
    }
}

@Preview
@Composable
fun OverviewScreenPreview(){
    OverviewScreen()
}