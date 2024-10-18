// UserSessionManager.kt
package com.example.tp3

import android.content.Context
import android.content.SharedPreferences

class UserSessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("UserSession", Context.MODE_PRIVATE)

    fun saveUserLogin(isLoggedIn: Boolean) {
        prefs.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
    }

    fun isUserLoggedIn(): Boolean {
        return prefs.getBoolean("isLoggedIn", false)
    }

    fun logoutUser() {
        saveUserLogin(false)
    }
}
