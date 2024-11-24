package com.example.tp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tp3.data.Product
import com.example.tp3.ui.theme.Tp3Theme

@Composable
fun HomeScreen(
    products: List<Product>,
    onAddProduct: (Product) -> Unit,
    onDeleteProduct: (Product) -> Unit,
    onUpdateProduct: (Product) -> Unit,
    onLogout: () -> Unit,
    onProductClick: (Product) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var productToEdit by remember { mutableStateOf<Product?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = { onLogout() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Se déconnecter")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Bienvenue", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Chez JR Building", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { product ->
                ProductItem(
                    product = product,
                    onEdit = { productToEdit = it; isEditing = true },
                    onDelete = { onDeleteProduct(it) },
                    onClick = { onProductClick(it) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val newProduct = Product(
                id = 0, // ID should be auto-generated in the database
                name = "New Product",
                price = 0.0,
                desctiption = "Description of the new product",
                imgURL = R.drawable.mk677
            )
            onAddProduct(newProduct)
        }) {
            Text("Add New Product")
        }
    }

    if (isEditing && productToEdit != null) {
        EditProductDialog(
            product = productToEdit!!,
            onDismiss = {
                isEditing = false
                productToEdit = null
            },
            onSave = { updatedProduct ->
                onUpdateProduct(updatedProduct)
                isEditing = false
                productToEdit = null
            }
        )
    }
}


@Composable
fun ProductItem(
    product: Product,
    onEdit: (Product) -> Unit,
    onDelete: (Product) -> Unit,
    onClick: (Product) -> Unit // Add this parameter to handle clicks
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(product) }, // Handle click here
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = product.imgURL),
            contentDescription = "Product Image",
            modifier = Modifier.size(120.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = product.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = "${product.price} €", style = MaterialTheme.typography.bodyMedium)
            Text(text = product.desctiption, style = MaterialTheme.typography.bodySmall)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { onEdit(product) }) {
                Text("Edit")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { onDelete(product) }) {
                Text("Delete")
            }
        }
    }
}

@Composable
fun EditProductDialog(
    product: Product,
    onDismiss: () -> Unit,
    onSave: (Product) -> Unit
) {
    var name by remember { mutableStateOf(product.name) }
    var price by remember { mutableStateOf(product.price.toString()) }
    var description by remember { mutableStateOf(product.desctiption) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                val updatedProduct = product.copy(
                    name = name,
                    price = price.toDoubleOrNull() ?: product.price,
                    desctiption = description // Fixed typo here
                )
                onSave(updatedProduct)
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        },
        title = { Text("Edit Product") },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                TextField(value = price, onValueChange = { price = it }, label = { Text("Price") })
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
            }
        }
    )
}
