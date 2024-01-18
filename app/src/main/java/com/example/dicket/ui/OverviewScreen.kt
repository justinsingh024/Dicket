package com.example.dicket.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dicket.R
import com.example.dicket.data.entity.Event
import java.io.File
import java.io.FileInputStream
import java.time.format.DateTimeFormatter

/**
 * Composable function for displaying the overview screen.
 *
 * @param modifier Modifier for customizing the layout.
 * @param viewModel ViewModel for managing the overview screen logic.
 * @param onOpenDetail Callback function to handle opening details for a specific event.
 */
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel(),
    onOpenDetail: (Event) -> Unit,
) {
    // Collect state from the ViewModel
    val uiState by viewModel
        .uiState.collectAsState()
    val displayEvents by viewModel.displayEvents.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val sortBy by viewModel.sortedBy.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    // Main Column
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Search and Sort Row
        Row(modifier = Modifier.fillMaxWidth()) {
            // Search TextField
            TextField(
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color(0xFFC4D3F0),
                    unfocusedTextColor = Color(0xFFC4D3F0),
                    focusedContainerColor = Color(0xFF1F293D),
                    unfocusedContainerColor = Color(0xFF111620),
                    disabledContainerColor = Color(0xFF111620),
                    cursorColor = Color(0xFFC4D3F0),
                    focusedBorderColor = Color(0xFF1F293D),
                    unfocusedBorderColor = Color(0xFF1F293D),
                ),
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .weight(4f),
                label = { Text("Search Events", color = Color.White) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color(0xFF637394)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Hier können Sie auf das Klicken der Entertaste reagieren
                        keyboardController?.hide()
                        viewModel.onSearchPressed()
                    }
                )
            )
            // Sort Dropdown Menu
            SortDropdownMenu(
                modifier = Modifier
                    .weight(2f)
                    .border(1.dp, color = Color(0xFF1F293D)),
                onSortedBy = { viewModel.onSortBy(it) },
                sortedBy = sortBy
            )

        }
        // Event Grid
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp), // Adjust the vertical spacing as needed
            horizontalArrangement = Arrangement.spacedBy(22.dp), // Adjust the horizontal spacing as needed
            modifier = modifier
        ) {
            items(displayEvents) { event ->
                // Event Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    ),

                    ) {
                }
                // Event Content
                Column(modifier = modifier
                    .padding(bottom = 20.dp)
                    .clickable {
                        onOpenDetail(event)
                    }) {
                    Card(
                        // Event Image
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp)) // Adjust the corner radius as needed
                            .shadow(10.dp) // Adjust the shadow elevation as needed
                    ) {
                        val defaultBitmap = painterResource(R.drawable.example_party)
                        val bitmap: ImageBitmap? = loadImageFromInternalStorage(
                            LocalContext.current,
                            event.image
                        )?.asImageBitmap()
                        if (bitmap != null) {
                            Image(
                                bitmap = bitmap,
                                contentDescription = "Party",
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .size(190.dp) // Adjust the size as needed
                            )
                        } else {
                            Image(
                                painter = defaultBitmap,
                                contentDescription = "Party",
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .size(190.dp) // Adjust the size as needed
                            )
                        }

                    }
                    // Event Title
                    Row(
                        modifier = modifier,

                        ) {
                        Text(
                            text = event.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                    // Event Date
                    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                    Text(
                        text = " " + event.date.format(formatter),
                        fontWeight = FontWeight.Light,
                        fontSize = 12.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = modifier.padding(2.dp)
                    )
                }

            }
        }
    }
}

/**
 * Composable function for displaying a dropdown menu for sorting events.
 *
 * @param modifier Modifier for customizing the layout.
 * @param sortedBy The currently selected sorting option.
 * @param onSortedBy Callback function to handle changes in the sorting option.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortDropdownMenu(
    modifier: Modifier = Modifier,
    sortedBy: SortBy,
    onSortedBy: (sortBy: SortBy) -> Unit,
) {
    // State for tracking the dropdown menu's expansion state
    var isExpanded by remember {
        mutableStateOf(false)
    }

    // Dropdown menu components
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isExpanded,
        onExpandedChange = { newValue ->
            isExpanded = newValue
        }
    ) {
        TextField(
            value = sortedBy.text,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            placeholder = {
                Text(text = "Sort by")
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                unfocusedContainerColor = Color(
                    0xFF111620
                ),
                focusedContainerColor = Color(0xFF1F293D),
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = Color(0xFFC4D3F0),
            ),
            modifier = Modifier.menuAnchor()
        )
        // Dropdown menu items
        ExposedDropdownMenu(
            modifier = Modifier.background(Color(0xFF1F293D)),
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            // Dropdown menu item for "None" sorting
            DropdownMenuItem(
                modifier = Modifier.background(Color(0xFF1F293D)),
                text = {
                    Text(text = SortBy.NONE.text)
                },
                onClick = {
                    isExpanded = false
                    onSortedBy(SortBy.NONE)

                },
                colors = MenuDefaults.itemColors(
                    textColor = Color.White
                )
            )
            // Dropdown menu item for sorting by name
            DropdownMenuItem(
                text = {
                    Text(text = SortBy.NAME.text)
                },
                onClick = {
                    isExpanded = false
                    onSortedBy(SortBy.NAME)

                },
                colors = MenuDefaults.itemColors(
                    textColor = Color.White
                )
            )
            // Dropdown menu item for sorting by date
            DropdownMenuItem(
                text = {
                    Text(text = SortBy.DATE.text)
                },
                onClick = {
                    isExpanded = false
                    onSortedBy(SortBy.DATE)

                },
                colors = MenuDefaults.itemColors(
                    textColor = Color.White
                )
            )
        }
    }
}

/**
 * Function to load an image from internal storage.
 *
 * @param context The application context.
 * @param fileName The name of the image file to be loaded.
 * @return The loaded Bitmap image, or null if an error occurs.
 */
private fun loadImageFromInternalStorage(context: Context, fileName: String): Bitmap? {
    try {
        val eventImagesDir = File(context.filesDir, "event_images")
        val imageFile = File(eventImagesDir, fileName)

        if (imageFile.exists()) {
            FileInputStream(imageFile).use { inputStream ->
                return BitmapFactory.decodeStream(inputStream)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

/**
 * Preview composable function for the overview screen.
 */
@Preview
@Composable
fun OverviewScreenPreview() {
    OverviewScreen(
        onOpenDetail = {}
    )
}