package org.example.moviekmm4.Model

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val results: List<MovieData>
)

