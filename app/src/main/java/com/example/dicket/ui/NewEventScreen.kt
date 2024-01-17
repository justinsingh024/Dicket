package com.example.dicket.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlin.reflect.KFunction12

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NewEventScreen(
    viewModel: OverviewViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onCreateEvent: KFunction12<String, String, String, String, String, String, String, String, String, String, String, String, Unit>

) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var minAge by remember { mutableStateOf("") }
    var entry by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var latestCancelingDate by remember { mutableStateOf("") }
    var maxQuantityTicket by remember { mutableStateOf("") }
    var organizer by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    var expandedCategory by remember { mutableStateOf(false) }
    var expandedLocation by remember { mutableStateOf(false) }
    val categories = viewModel.getAllCategories()
    val locations = viewModel.getAllLocations()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = ScrollState(0), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Create new event",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            fontSize = 32.sp,
            color = Color.White
        )
        TextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = { Text("Title", color = Color(0xFFC4D3F0)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFFC4D3F0),
                unfocusedTextColor = Color(0xFFC4D3F0),
                focusedContainerColor = Color(0xFF1F293D),
                unfocusedContainerColor = Color(0xFF1F293D),
                disabledContainerColor = Color(0xFF1F293D),
                cursorColor = Color(0xFFC4D3F0),
                focusedBorderColor = Color(0xFFC4D3F0),
                unfocusedBorderColor = Color(0xFFC4D3F0),
            ),
        )
        TextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = { Text("Description", color = Color(0xFFC4D3F0)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFFC4D3F0),
                unfocusedTextColor = Color(0xFFC4D3F0),
                focusedContainerColor = Color(0xFF1F293D),
                unfocusedContainerColor = Color(0xFF1F293D),
                disabledContainerColor = Color(0xFF1F293D),
                cursorColor = Color(0xFFC4D3F0),
                focusedBorderColor = Color(0xFFC4D3F0),
                unfocusedBorderColor = Color(0xFFC4D3F0),
            ),
        )
        TextField(
            value = price,
            onValueChange = { price = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = { Text("Price", color = Color(0xFFC4D3F0)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFFC4D3F0),
                unfocusedTextColor = Color(0xFFC4D3F0),
                focusedContainerColor = Color(0xFF1F293D),
                unfocusedContainerColor = Color(0xFF1F293D),
                disabledContainerColor = Color(0xFF1F293D),
                cursorColor = Color(0xFFC4D3F0),
                focusedBorderColor = Color(0xFFC4D3F0),
                unfocusedBorderColor = Color(0xFFC4D3F0),
            ),
        )
        TextField(
            value = minAge,
            onValueChange = { minAge = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = { Text("Minimum Age", color = Color(0xFFC4D3F0)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFFC4D3F0),
                unfocusedTextColor = Color(0xFFC4D3F0),
                focusedContainerColor = Color(0xFF1F293D),
                unfocusedContainerColor = Color(0xFF1F293D),
                disabledContainerColor = Color(0xFF1F293D),
                cursorColor = Color(0xFFC4D3F0),
                focusedBorderColor = Color(0xFFC4D3F0),
                unfocusedBorderColor = Color(0xFFC4D3F0),
            ),
        )
        TextField(
            value = entry,
            onValueChange = { entry = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = { Text("Entry time", color = Color(0xFFC4D3F0)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFFC4D3F0),
                unfocusedTextColor = Color(0xFFC4D3F0),
                focusedContainerColor = Color(0xFF1F293D),
                unfocusedContainerColor = Color(0xFF1F293D),
                disabledContainerColor = Color(0xFF1F293D),
                cursorColor = Color(0xFFC4D3F0),
                focusedBorderColor = Color(0xFFC4D3F0),
                unfocusedBorderColor = Color(0xFFC4D3F0),
            ),
        )
        TextField(
            value = date,
            onValueChange = { date = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = { Text("Date", color = Color(0xFFC4D3F0)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFFC4D3F0),
                unfocusedTextColor = Color(0xFFC4D3F0),
                focusedContainerColor = Color(0xFF1F293D),
                unfocusedContainerColor = Color(0xFF1F293D),
                disabledContainerColor = Color(0xFF1F293D),
                cursorColor = Color(0xFFC4D3F0),
                focusedBorderColor = Color(0xFFC4D3F0),
                unfocusedBorderColor = Color(0xFFC4D3F0),
            ),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1F293D))
                .heightIn(max = 80.dp)
                .padding(bottom = 16.dp)
                .clickable {
                    expandedLocation = !expandedLocation
                }
        ) {
            Text(
                text = if (location.isEmpty()) "Location" else location,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color(0xFFC4D3F0)
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown Icon",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(24.dp),
                tint = Color(0xFFC4D3F0)
            )

            // Dropdown menu
            DropdownMenu(
                expanded = expandedLocation,
                onDismissRequest = { expandedLocation = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1F293D))
            ) {
                locations.forEach { locationItem ->
                    DropdownMenuItem(
                        modifier = Modifier.background(Color(0xFF1F293D)),
                        text = {
                            Text(text = locationItem.locationName, color = Color(0xFFC4D3F0))
                        }, onClick = {
                            location = locationItem.locationID.toString()
                            expandedLocation = false
                        })
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1F293D))
                .heightIn(max = 70.dp)
                .padding(bottom = 16.dp)
                .clickable {
                    expandedCategory = !expandedCategory
                }
        ) {
            Text(
                text = if (category.isEmpty()) "Category" else category,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color(0xFFC4D3F0)
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown Icon",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(24.dp),
                tint = Color(0xFFC4D3F0)
            )

            // Dropdown menu
            DropdownMenu(
                expanded = expandedCategory,
                onDismissRequest = { expandedCategory = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1F293D))
            ) {
                categories.forEach { categoryItem ->
                    DropdownMenuItem(
                        modifier = Modifier.background(Color(0xFF1F293D)),
                        text = {
                            Text(text = categoryItem.name, color = Color(0xFFC4D3F0))
                        }, onClick = {
                            category = categoryItem.categoryID.toString()
                            expandedCategory = false
                        })
                }
            }
        }
        TextField(
            value = latestCancelingDate,
            onValueChange = { latestCancelingDate = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = { Text("Latest Canceling date", color = Color(0xFFC4D3F0)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFFC4D3F0),
                unfocusedTextColor = Color(0xFFC4D3F0),
                focusedContainerColor = Color(0xFF1F293D),
                unfocusedContainerColor = Color(0xFF1F293D),
                disabledContainerColor = Color(0xFF1F293D),
                cursorColor = Color(0xFFC4D3F0),
                focusedBorderColor = Color(0xFFC4D3F0),
                unfocusedBorderColor = Color(0xFFC4D3F0),
            ),
        )
        TextField(
            value = maxQuantityTicket,
            onValueChange = { maxQuantityTicket = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = { Text("Max quantity tickets", color = Color(0xFFC4D3F0)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFFC4D3F0),
                unfocusedTextColor = Color(0xFFC4D3F0),
                focusedContainerColor = Color(0xFF1F293D),
                unfocusedContainerColor = Color(0xFF1F293D),
                disabledContainerColor = Color(0xFF1F293D),
                cursorColor = Color(0xFFC4D3F0),
                focusedBorderColor = Color(0xFFC4D3F0),
                unfocusedBorderColor = Color(0xFFC4D3F0),
            ),
        )
        TextField(
            value = organizer,
            onValueChange = { organizer = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = { Text("Organizer", color = Color(0xFFC4D3F0)) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFFC4D3F0),
                unfocusedTextColor = Color(0xFFC4D3F0),
                focusedContainerColor = Color(0xFF1F293D),
                unfocusedContainerColor = Color(0xFF1F293D),
                disabledContainerColor = Color(0xFF1F293D),
                cursorColor = Color(0xFFC4D3F0),
                focusedBorderColor = Color(0xFFC4D3F0),
                unfocusedBorderColor = Color(0xFFC4D3F0),
            ),
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(
                    255,
                    128,
                    54
                )
            ), // Change the color as needed
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            onClick = {
                onCreateEvent(
                    title,
                    description,
                    price,
                    minAge,
                    entry,
                    date,
                    location,
                    image,
                    category,
                    latestCancelingDate,
                    maxQuantityTicket,
                    organizer
                )
            }
        ) {
            Text(text = "Save", fontSize = 18.sp) // Adjust the font size as needed)
        }
    }
}

@Composable
@Preview
fun NewEventScreenPreview() {
    //NewEventScreen()
}