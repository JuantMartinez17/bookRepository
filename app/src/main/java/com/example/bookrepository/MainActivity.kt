package com.example.bookrepository

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookrepository.ViewModel.GenreViewModel
import com.example.bookrepository.adapter.GenreAdapter
import com.example.bookrepository.data.Genre
import com.example.bookrepository.data.GenreRepository
import com.example.bookrepository.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var genreViewModel: GenreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        genreViewModel = GenreViewModel()

        genreViewModel.genreList.observe(this){ genre ->
            initRecycler(GenreRepository.listOfGenres)
        }


    }

    private fun initRecycler (genres: List<Genre>){
        binding.rvGenres.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvGenres.adapter = GenreAdapter(genres)
    }
}