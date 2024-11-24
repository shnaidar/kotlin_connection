package com.example.tp3.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DATABASE_VERSION = 1
private const val DATABASE_NAME = "ProductDatabase.db"

// SQL statement to create the "product" table
private val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${ProductContract.ProductEntry.TABLE_NAME} (" +
            "${ProductContract.ProductEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY," +
            "${ProductContract.ProductEntry.COLUMN_NAME_NAME} TEXT NOT NULL," +
            "${ProductContract.ProductEntry.COLUMN_NAME_PRICE} REAL NOT NULL," +
            "${ProductContract.ProductEntry.COLUMN_NAME_DESCRIPTION} TEXT," +
            "${ProductContract.ProductEntry.COLUMN_NAME_IMG_URL} INTEGER)"

private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ProductContract.ProductEntry.TABLE_NAME}"

class ProductDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES) // Execute SQL to create the table
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)  // Drop the old table if it exists
        onCreate(db) // Create a new table
    }
}
