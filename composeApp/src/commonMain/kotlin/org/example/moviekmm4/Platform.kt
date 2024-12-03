package org.example.moviekmm4

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform