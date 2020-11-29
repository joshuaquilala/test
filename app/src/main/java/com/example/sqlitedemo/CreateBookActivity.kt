package com.example.sqlitedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sqlitedemo.models.Book

class CreateBookActivity : AppCompatActivity() {
    lateinit var addBookButton: Button
    lateinit var  titleET: EditText
    lateinit var authorET: EditText
    lateinit var  priceET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)

        val databaseHandler = BooksTableHandler(this)
        titleET = findViewById(R.id.bookAuthorET)
        authorET = findViewById(R.id.bookAuthorET)
        priceET = findViewById(R.id.PriceET)

        addBookButton = findViewById(R.id.addBookButton)
        addBookButton.setOnClickListener{
            // get th fields from the form
            val title = titleET.text.toString()
            val author = authorET.text.toString()
            val price = authorET.text.toString().toDouble()
            // assign it to a book model
            val book = Book(title, author, price)
            // save it to the database
            databaseHandler.create(book)

            if (databaseHandler.create(book)){
                 Toast.makeText(applicationContext, "book was added.", Toast.LENGTH_SHORT).show()

            } else{
                Toast.makeText(applicationContext, "Oops, i did it again!", Toast.LENGTH_SHORT).show()
            }
            clearFields()

        }
    }
    fun clearFields() {
        titleET.text.clear()
        authorET.text.clear()

    }
}