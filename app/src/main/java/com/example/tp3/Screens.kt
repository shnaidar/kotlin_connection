package com.example.tp3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(onLogin: (String, String) -> Unit,
                onNavigateToSignUp: () -> Unit,
                onNavigateToResetPassword: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Connexion", style = MaterialTheme.typography.headlineLarge)

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    onLogin(email, password)
                } else {
                    errorMessage = "Veuillez remplir tous les champs."
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text(text = "Se connecter")
        }

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }
        TextButton(
            onClick = onNavigateToSignUp,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Créer un compte", color = MaterialTheme.colorScheme.primary)
        }

        TextButton(
            onClick = onNavigateToResetPassword,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Mot de passe oublié ?", color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun SignUpScreen(onSignUp: (String, String, String) -> Unit,
                 onNavigateToLogin: () -> Unit

) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Créer un compte", style = MaterialTheme.typography.headlineLarge)

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nom") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmer le mot de passe") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = {
                if (name.isNotEmpty() && email.isNotEmpty() && password == confirmPassword) {
                    onSignUp(name, email, password)
                } else {
                    errorMessage = "Les informations ne sont pas valides."
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text(text = "S'inscrire")
        }

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }
        TextButton(
            onClick = onNavigateToLogin,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Déjà un compte ? Se connecter", color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun ResetPasswordScreen(onReset: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var confirmationMessage by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Réinitialiser le mot de passe", style = MaterialTheme.typography.headlineLarge)

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (email.isNotEmpty()) {
                    onReset(email)
                    confirmationMessage = "Un email de réinitialisation a été envoyé."
                } else {
                    errorMessage = "Veuillez entrer une adresse e-mail valide."
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text(text = "Réinitialiser")
        }

        if (confirmationMessage.isNotEmpty()) {
            Text(text = confirmationMessage, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = 8.dp))
        }

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }
    }
}


