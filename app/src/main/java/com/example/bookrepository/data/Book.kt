package com.example.bookrepository.data

import java.io.Serializable

data class Book(
    val title: String,
    val writer: String,
    val genre: String,
    val synopsis: String,
) : Serializable //para poder enviar en intents
