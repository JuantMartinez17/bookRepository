package com.example.bookrepository.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bookrepository.MyBooksActivity
import com.example.bookrepository.data.Genre
import com.example.bookrepository.databinding.GenreItemBinding

class GenreViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = GenreItemBinding.bind(view)

    fun bind(genre: Genre){
        binding.tvGenreName.text = genre.genre
        binding.btnGenre.setOnClickListener{
            val intent = Intent(itemView.context, MyBooksActivity::class.java)
            intent.putExtra("genre", genre.genre)
            itemView.context.startActivity(intent)
        }
    }
}