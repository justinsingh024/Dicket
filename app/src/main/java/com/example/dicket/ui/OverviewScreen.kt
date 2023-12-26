package com.example.dicket.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier,
){
    Column(modifier = modifier) {
        Text(text = "Test")
    }
}

@Preview
@Composable
fun OverviewScreenPreview(){
    OverviewScreen()
}