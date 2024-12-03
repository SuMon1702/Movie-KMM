package org.example.moviekmm.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders

import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

import org.example.moviekmm4.Model.MovieResponse


class NetworkClient {
    private val client = HttpClient{
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
        install(Logging){
            level = LogLevel.BODY
        }
    }
    suspend fun fetchPopularMovies(): MovieResponse {
        val response: HttpResponse = client.get("https://api.themoviedb.org/3/movie/popular?language=en-US&page=1"){
            parameter("language","en-US")
            parameter("page",1)
            header(HttpHeaders.Authorization,"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMmQ0YTY4NDdhYzU0MDk5OWY4OTc0OGRiNmVkMTVjYSIsIm5iZiI6MTczMjAyNjMxOC4wMTMwNDI3LCJzdWIiOiI2MjBkZmQwOWJlZmQ5MTAwNmVmZTQ1ZmEiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.PGDTCi1XTedoQK97K1xBw-sfMMMudXZuAVbmLvkNSUc")
            header(HttpHeaders.Accept,"application/json")
        }
        return response.body()
    }
}