package com.example.tp3.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.example.tp3.data.Product

class ProductDao(context: Context) {
    private val dbHelper = ProductDbHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    // Insert a product
    fun insertProduct(name: String, price: Double, description: String, imgUrl: Int): Long {
        val values = ContentValues().apply {
            put(ProductContract.ProductEntry.COLUMN_NAME_NAME, name)
            put(ProductContract.ProductEntry.COLUMN_NAME_PRICE, price)
            put(ProductContract.ProductEntry.COLUMN_NAME_DESCRIPTION, description)
            put(ProductContract.ProductEntry.COLUMN_NAME_IMG_URL, imgUrl)
        }
        return db.insert(ProductContract.ProductEntry.TABLE_NAME, null, values)
    }


    // Get all products
    fun getAllProducts(): List<Product> {
        val products = mutableListOf<Product>()
        val projection = arrayOf(
            ProductContract.ProductEntry.COLUMN_NAME_ID,
            ProductContract.ProductEntry.COLUMN_NAME_NAME,
            ProductContract.ProductEntry.COLUMN_NAME_PRICE,
            ProductContract.ProductEntry.COLUMN_NAME_DESCRIPTION,
            ProductContract.ProductEntry.COLUMN_NAME_IMG_URL
        )
        val cursor: Cursor = db.query(
            ProductContract.ProductEntry.TABLE_NAME,
            projection,
            null, null, null, null, null
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(ProductContract.ProductEntry.COLUMN_NAME_ID))
                val name = getString(getColumnIndexOrThrow(ProductContract.ProductEntry.COLUMN_NAME_NAME))
                val price = getDouble(getColumnIndexOrThrow(ProductContract.ProductEntry.COLUMN_NAME_PRICE)) // Corrected to getDouble
                val description = getString(getColumnIndexOrThrow(ProductContract.ProductEntry.COLUMN_NAME_DESCRIPTION))
                val imgUrl = getInt(getColumnIndexOrThrow(ProductContract.ProductEntry.COLUMN_NAME_IMG_URL))

                products.add(Product(id, name, price, description, imgUrl)) // Pass all required fields
            }
        }
        cursor.close()
        return products
    }


    // Delete all products
    fun deleteAllProducts() {
        db.delete(ProductContract.ProductEntry.TABLE_NAME, null, null)
    }

    // Close the database
    fun close() {
        dbHelper.close()
    }
}
