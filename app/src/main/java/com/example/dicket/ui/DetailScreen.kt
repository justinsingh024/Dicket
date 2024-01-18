package com.example.dicket.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dicket.R
import com.example.dicket.data.entity.Category
import com.example.dicket.data.entity.Event
import com.example.dicket.data.entity.Location
import java.io.File
import java.io.FileInputStream
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Composable function to display detailed information about an event.
 *
 * @param modifier Modifier for styling the DetailScreen layout.
 * @param event Event object containing details about the event.
 * @param category Category object representing the category of the event.
 * @param location Location object containing details about the event location.
 * @param onBuyPressed Callback function invoked when the "Order Ticket" button is pressed.
 */
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    event: Event,
    categorie: Category,
    location: Location,
    onBuyPressed: (Event) -> Unit
) {
    Column {
        val defaultBitmap = painterResource(R.drawable.example_party)
        val bitmap: ImageBitmap? =
            loadImageFromInternalStorage(LocalContext.current, event.image)?.asImageBitmap()

        if (bitmap != null) {
            Image(
                bitmap = bitmap,
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .weight(2f),

                )
        } else {
            Image(
                painter = defaultBitmap,
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .weight(2f),

                )
        }


        Column(
            modifier = modifier
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
                .weight(4f)
        ) {
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            Text(
                text = event.title,
                fontSize = 30.sp,
                fontStyle = FontStyle.Italic,
                color = Color.White
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(text = event.description, color = Color.White)
            Spacer(modifier = modifier.height(16.dp))
            StarRating(rating = event.rating)
            PaddedText(label = "Price:", value = String.format("%.2f€", event.price))
            PaddedText(label = "Location:", value = location.locationName)
            PaddedText(label = "Entry: ", value = event.entry.toString())
            PaddedText(label = "Date: ", value = event.date.format(formatter))
            PaddedText(label = "Category: ", value = categorie.name)
            PaddedText(label = "Min Age: ", value = event.minAge.toString())
            PaddedText(label = "Available Tickets: ", value = event.maxQuantityTicket.toString())
        }
        Button(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.75f)
                .padding(12.dp),
            onClick = {
                onBuyPressed(event)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(
                    255,
                    128,
                    54
                )
            ), // Change the color as needed
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Order Ticket", fontSize = 18.sp)
        }
    }
}

/**
 * Function to load an image from internal storage.
 *
 * @param context Context of the application.
 * @param fileName Name of the image file.
 * @return Bitmap object representing the loaded image.
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
 * Composable function to display a row with padded text.
 *
 * @param label Label for the text.
 * @param value Value to be displayed.
 * @param modifier Modifier for styling the row.
 */
@Composable
fun PaddedText(label: String, value: String, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(16.dp))
    Row {
        Text(
            text = label,
            modifier = modifier.weight(1f),
            color = Color(0xFF637394)
        )
        Text(
            text = value,
            modifier = modifier.weight(2f),
            color = Color.White
        )
    }

}

/**
 * Composable function to display a star rating.
 *
 * @param rating Rating value to be displayed.
 */
@Composable
fun StarRating(rating: Float) {
    LazyRow(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 0.dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
    ) {
        items(5) { index ->
            val filled = index < rating
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (filled) Color(255, 128, 54) else Color(0xFFD9D9D9),
                modifier = Modifier
                    .size(28.dp)
                    .padding(2.dp)
            )
        }
    }
}

/**
 * Preview function for DetailScreen with example data.
 */
@Preview
@Composable
fun DetailScreenPreview() {
    val exampleLocation = Location(
        locationID = 2,
        street = "Langestraße",
        locationName = "Kunsthalle",
        houseNumber = "8",
        city = "Karlsruhe",
        plz = 76135
    )

    val exampleCategory = Category(categoryID = 1, name = "Musik")

    val exampleEvent = Event(
        eventID = 1,
        title = "Amazing Event",
        rating = 4.5f,
        description = "Join us for an incredible experience!",
        minAge = 18,
        entry = LocalTime.of(18, 30),
        date = LocalDate.of(2023, 4, 20),  // Set to 24 hours from now
        location = 1,
        image = "https://example.com/sample_image.jpg",
        category = 1,
        price = 49.99,
        latestCancelingDate = System.currentTimeMillis() - 86400000,  // Set to 24 hours ago
        maxQuantityTicket = 200,
        organizer = 1
    )
    DetailScreen(
        event = exampleEvent,
        location = exampleLocation,
        categorie = exampleCategory,
        onBuyPressed = {})
}
