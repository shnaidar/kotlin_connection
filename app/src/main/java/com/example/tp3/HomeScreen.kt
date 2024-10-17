package com.example.tp3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { onLogout() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Logout")
        }


        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Bienvenue à la page d'accueil", style = MaterialTheme.typography.headlineLarge)

    }
}
