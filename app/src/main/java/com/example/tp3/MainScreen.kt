package com.example.tp3

import androidx.compose.runtime.*
import androidx.compose.material3.*

@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("login") }

    when (currentScreen) {
        "login" -> LoginScreen(onLogin = { email, password ->
            currentScreen = "signup"
        })
        "signup" -> SignUpScreen(onSignUp = { name, email, password ->
            currentScreen = "login"
        })
        "reset" -> ResetPasswordScreen(onReset = { email ->
        })
    }
}
