// MainScreen.kt
package com.example.tp3

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("login") }
    val userSessionManager = UserSessionManager(context = LocalContext.current)

    // Check if the user is already logged in
    if (userSessionManager.isUserLoggedIn()) {
        currentScreen = "home"
    }

    when (currentScreen) {
        "login" -> LoginScreen(
            onLogin = { email, password ->
                userSessionManager.saveUserLogin(true) // Save user session
                currentScreen = "home" // Navigate to home screen
            },
            onNavigateToSignUp = { currentScreen = "signup" },
            onNavigateToResetPassword = { currentScreen = "resetPassword" }
        )
        "signup" -> SignUpScreen(
            onSignUp = { name, email, password ->
                currentScreen = "login" // Navigate back to login after sign-up
            },
            onNavigateToLogin = { currentScreen = "login" }
        )
        "resetPassword" -> ResetPasswordScreen(
            onReset = { email ->
                // Handle password reset logic
                currentScreen = "login" // Navigate back to login after resetting password
            }
        )
        "home" -> HomeScreen(
            onLogout = {
                userSessionManager.logoutUser() // Clear session on logout
                currentScreen = "login" // Navigate back to login screen
            }
        )
    }
}
