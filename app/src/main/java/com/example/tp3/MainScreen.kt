package com.example.tp3

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import com.example.tp3.data.Product // Ensure Product is imported

@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("login") }
    val userSessionManager = UserSessionManager(context = LocalContext.current)

    // List of products for the demo
    val products = remember {
        mutableStateListOf(
            Product(1, "Product 1", 10.0, "Description 1", R.drawable.mk677),
            Product(2, "Product 2", 15.0, "Description 2", R.drawable.mk677)
        )
    }

    // Functions to handle product actions
    val onAddProduct: (Product) -> Unit = { newProduct ->
        products.add(newProduct)
    }

    val onDeleteProduct: (Product) -> Unit = { product ->
        products.remove(product)
    }

    val onUpdateProduct: (Product) -> Unit = { updatedProduct ->
        val index = products.indexOfFirst { it.id == updatedProduct.id }
        if (index != -1) {
            products[index] = updatedProduct
        }
    }

    // Check user login status on first launch
    LaunchedEffect(Unit) {
        if (userSessionManager.isUserLoggedIn()) {
            currentScreen = "home"
        }
    }

    // Navigate between screens based on the currentScreen state
    when (currentScreen) {
        "login" -> LoginScreen(
            onLogin = { email, password ->
                userSessionManager.saveUserLogin(true)
                currentScreen = "home"
            },
            onNavigateToSignUp = {
                currentScreen = "signup"
            },
            onNavigateToResetPassword = {
                currentScreen = "resetPassword"
            }
        )
        "signup" -> SignUpScreen(
            onSignUp = { name, email, password ->
                currentScreen = "login"
            },
            onNavigateToLogin = {
                currentScreen = "login"
            }
        )
        "resetPassword" -> ResetPasswordScreen(
            onReset = { email ->
                currentScreen = "login"
            }
        )
        "home" -> HomeScreen(
            products = products,
            onLogout = {
                userSessionManager.logoutUser()
                currentScreen = "login"
            },
            onProductClick = { product ->
                // Handle product click, navigate to product details
                currentScreen = "productDetail"
            },
            onAddProduct = onAddProduct,
            onDeleteProduct = onDeleteProduct,
            onUpdateProduct = onUpdateProduct
        )
        "productDetail" -> ProductDetailsScreen(
            name = products.first().name,
            description = products.first().desctiption,
            price = products.first().price,
            imageUrl = products.first().imgURL,
            onBack = { currentScreen = "home" }
        )

    }
}
