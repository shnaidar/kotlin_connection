package com.example.tp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tp3.MainScreen
import com.example.tp3.ui.theme.Tp3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tp3Theme {
                MainScreen()
            }
        }
    }
}
