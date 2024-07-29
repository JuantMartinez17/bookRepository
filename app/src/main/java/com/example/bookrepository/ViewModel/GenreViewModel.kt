package com.example.bookrepository.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookrepository.data.Genre
import com.example.bookrepository.data.GenreRepository

class GenreViewModel : ViewModel(){
    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>> = _genreList

    init {
        _genreList.value = GenreRepository.listOfGenres
    }
}