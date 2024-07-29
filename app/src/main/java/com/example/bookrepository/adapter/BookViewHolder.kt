package com.example.bookrepository.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bookrepository.data.Book
import com.example.bookrepository.databinding.BookItemBinding

class BookViewHolder (view: View): RecyclerView.ViewHolder(view){
    val binding = BookItemBinding.bind(view)

    fun bind(book: Book){
        binding.tvBookTitle.text = book.title
        binding.tvBookWriter.text = book.writer
    }
}