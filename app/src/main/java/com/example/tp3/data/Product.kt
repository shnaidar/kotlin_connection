package com.example.tp3.data

import android.R
import kotlinx.coroutines.internal.OpDescriptor
import java.net.URL

data class Product(
    val id: Long,
    val name: String,
    val price: Double,
    val desctiption: String ="",
    val imgURL: Int=0
)
