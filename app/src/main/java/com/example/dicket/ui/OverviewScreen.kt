package com.example.dicket.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.flow.StateFlow
import java.io.File
import java.io.FileInputStream
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel(),
    onOpenDetail: (Event) -> Unit,
) {
    val uiState by viewModel
        .uiState.collectAsState()
    val displayEvents by viewModel.displayEvents.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val sortBy by viewModel.sortedBy.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .weight(4f),
                label = { Text("Enter text") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
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
            SortDropdownMenu(modifier = Modifier.weight(2f), onSortedBy = { viewModel.onSortBy(it) }, sortedBy = sortBy)

        }
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
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),

                ) {
            }
            Column(modifier = modifier
                .padding(bottom = 20.dp)
                .clickable {
                    onOpenDetail(event)
                }) {
                Card(
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortDropdownMenu(
    modifier: Modifier = Modifier,
    sortedBy: SortBy,
    onSortedBy: (sortBy: SortBy) -> Unit,
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

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
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
        expanded = isExpanded,
        onDismissRequest = {
            isExpanded = false
        }
    ) {
        DropdownMenuItem(
            text = {
                Text(text = SortBy.NONE.text)
            },
            onClick = {
                isExpanded = false
                onSortedBy(SortBy.NONE)

            }
        )
        DropdownMenuItem(
            text = {
                Text(text = SortBy.NAME.text)
            },
            onClick = {
                isExpanded = false
                onSortedBy(SortBy.NAME)

            }
        )
        DropdownMenuItem(
            text = {
                Text(text = SortBy.DATE.text)
            },
            onClick = {
                isExpanded = false
                onSortedBy(SortBy.DATE)

            }
        )
    }
    }
}

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

@Preview
@Composable
fun OverviewScreenPreview() {
    OverviewScreen(
        onOpenDetail = {}
    )
}