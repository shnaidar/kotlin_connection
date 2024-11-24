package com.example.tp3

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext

@Composable
fun ProductDetailsScreen(
    name: String,
    description: String,
    price: Double,
    imageUrl: Int,
    onBack: () -> Unit // Add onBack here
) {
    var review by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = onBack, // Call the onBack function when the button is clicked
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Back to Home")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = imageUrl),
            contentDescription = "Product Image",
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Name: $name", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Description: $description", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Price: $price €", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = review,
            onValueChange = { review = it },
            label = { Text("Write your review") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                Toast.makeText(context, "Review submitted: $review", Toast.LENGTH_SHORT).show()
                review = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit Review")
        }
    }
}

