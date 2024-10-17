package com.example.tp3

import androidx.compose.runtime.*
import androidx.compose.material3.*

@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("login") }
    var isLoggedIn by remember { mutableStateOf(false) }  // Pour savoir si l'utilisateur est connecté

    when (currentScreen) {
        "login" -> LoginScreen(
            onLogin = { email, password ->
                currentScreen = "signup"
                isLoggedIn = true
                currentScreen = "home"
            },
            onNavigateToSignUp = { currentScreen = "signup" },
            onNavigateToResetPassword = { currentScreen = "resetPassword" }
        )
        "signup" -> SignUpScreen(onSignUp = { name, email, password ->
            currentScreen = "login"
        },
            onNavigateToLogin = { currentScreen = "login" }        )
        "resetPassword" -> ResetPasswordScreen(onReset = { email ->

        })
        "home" -> HomeScreen(
            onLogout = {
                currentScreen = "login"            }
        )
    }
}
