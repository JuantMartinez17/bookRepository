package com.example.bookrepository

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookrepository.ViewModel.BookViewModel
import com.example.bookrepository.adapter.BookAdapter
import com.example.bookrepository.data.Book
import com.example.bookrepository.data.BookRepository
import com.example.bookrepository.databinding.ActivityMyBooksBinding
import com.example.bookrepository.databinding.DialogBookBinding

class MyBooksActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyBooksBinding
    private lateinit var bookViewModel: BookViewModel
    private lateinit var adapter: BookAdapter
    private lateinit var dialogBookBinding: DialogBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMyBooksBinding.inflate(layoutInflater)
        dialogBookBinding = DialogBookBinding.inflate(layoutInflater)
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(dialogBookBinding.root)
        val alertDialog = alertDialogBuilder.create()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val genreName = intent.getStringExtra("genre")
        binding.tvBooks.text = genreName

        bookViewModel = BookViewModel()
        bookViewModel.bookList.observe(this){ book ->
            initRecycler(BookRepository.listOfBooks)
        }

        setListener(alertDialog)

        binding.btnBack.setOnClickListener {
            finish()
        }

    }

    private fun setListener(alertDialog: AlertDialog) {
        binding.fabAddBook.setOnClickListener {
            showAlertDialog(alertDialog)
        }
    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        alertDialog.show()
        clearEditTexts()
        dialogBookBinding.btnAddBook.setOnClickListener {
            val title = addTitleBook()
            val writer = addWriterBook()
            val genre = addGenreBook()
            val synopsis = addSynopsisBook()
            if (title.isNotEmpty() && writer.isNotEmpty() && genre.isNotEmpty() && synopsis.isNotEmpty()) {
                bookViewModel.addBook(title, writer, genre, synopsis)
                alertDialog.dismiss()
            }
        }
    }

    private fun addSynopsisBook(): String {
        val synopsis = dialogBookBinding.etSynopsis.text.toString()
        return synopsis
    }

    private fun addGenreBook(): String {
        val genre = dialogBookBinding.etGenre.text.toString()
        return genre
    }

    private fun addWriterBook(): String {
        val writer = dialogBookBinding.etWriter.text.toString()
        return writer
    }

    private fun addTitleBook(): String {
        val title = dialogBookBinding.etTitle.text.toString()
        return title
    }

    private fun clearEditTexts() {
        dialogBookBinding.etTitle.text.clear()
        dialogBookBinding.etWriter.text.clear()
        dialogBookBinding.etGenre.text.clear()
        dialogBookBinding.etSynopsis.text.clear()
    }

    private fun initRecycler(listOfBooks: List<Book>) {
        adapter = BookAdapter(listOfBooks.toMutableList()){ selectedBook ->
            val intent = Intent(this, BookActivity::class.java)
            intent.putExtra("book", selectedBook)
            startActivity(intent)
        }
        binding.rvBooks.layoutManager = LinearLayoutManager(this)
        binding.rvBooks.adapter = adapter
    }
}