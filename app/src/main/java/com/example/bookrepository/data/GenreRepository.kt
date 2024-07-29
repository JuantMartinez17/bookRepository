package com.example.bookrepository.data


class GenreRepository {
    companion object {
        val listOfGenres = mutableListOf<Genre>(
            Genre("Ficción"),
            Genre("Terror"),
            Genre("Infantil"),
        )
    }

    fun getGenres(): List<Genre> = listOfGenres;
}