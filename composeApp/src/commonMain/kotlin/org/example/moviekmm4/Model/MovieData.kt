package org.example.moviekmm4.Model

import kotlinx.serialization.Serializable


@Serializable
data class MovieData(
    val title: String,
    val description: String,
    val poster_path:String,
)

