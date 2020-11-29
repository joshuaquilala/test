package com.example.sqlitedemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlitedemo.models.Book


class BooksTableHandler (var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION ) {
    companion object {
        private  val DATABASE_VERSION = 1
        private  val DATABASE_NAME = "books_database"
        private val TABLE_NAME = "books"
        private val COL_ID ="id"
        private val COL_TITLE = "title"
        private val COL_AUTHOR = "author"
        private val COL_PRICE = "price"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
        //define query
        val query = "CREATE TABLE" + TABLE_NAME + "( " + COL_ID + " INTEGER PRIMARY KEY, " + COL_TITLE + "TEXT, " + COL_AUTHOR+ "TEXT, " + COL_PRICE + "DECIMAL(10,2))"
        //exec query
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
        db!!.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(db)
    }
    fun create(book: Book): Boolean {
        // get the database
        val database = this.writableDatabase
        // set the contentValues
        val contentValues = ContentValues()
        contentValues.put(COL_TITLE, book.title)
        contentValues.put(COL_AUTHOR, book.author)
        val convertedPrice = String.format("%.2f", book.price).toDouble()
        contentValues.put(COL_PRICE, convertedPrice)
        //insert
        val result = database.insert(TABLE_NAME, null, contentValues)

        if(result == (0).toLong()){
            return true
        }
        return false

        return true
    }
}