package com.example.bookrepository.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookrepository.BookActivity
import com.example.bookrepository.R
import com.example.bookrepository.data.Book

class BookAdapter (
    private val listOfBooks: List<Book>,
    private val onBookClick: (Book) -> Unit): RecyclerView.Adapter<BookViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BookViewHolder(layoutInflater.inflate(R.layout.book_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listOfBooks.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = listOfBooks[position]
        holder.bind(book)
        holder.itemView.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, BookActivity::class.java)
            intent.putExtra("book", book)
            context.startActivity(intent)
        }
    }

}