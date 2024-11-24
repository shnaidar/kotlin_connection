package com.example.tp3.data

import android.provider.BaseColumns

object ProductContract {
    object ProductEntry : BaseColumns {
        const val TABLE_NAME = "product"
        const val COLUMN_NAME_ID = "_id"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_PRICE = "price"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_IMG_URL = "imgurl"

    }
}
