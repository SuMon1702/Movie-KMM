package org.example.moviekmm4

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.moviekmm.network.NetworkClient
import org.example.moviekmm4.Model.MovieData
import org.example.moviekmm4.View.MovieItemView

@Composable
fun Homepage() {
    var searchQuery = remember { mutableStateOf("") }
    var popularMovies = remember { mutableStateListOf<MovieData>() }

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            try {
                val movieResponse = NetworkClient().fetchPopularMovies()
                popularMovies.clear()
                popularMovies.addAll(movieResponse.results)
            } catch (e: Exception) {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Failed to fetch movies: ${e.message}")
                }
                // Handle the error
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        content = {

            Box(
                modifier = Modifier.fillMaxSize()
                    .background(Color.Black)
            )
            {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        BasicTextField(
                            value = searchQuery.value,
                            onValueChange = { searchQuery.value = it },
                            modifier = Modifier.fillMaxWidth()
                                .padding(8.dp)
                                .height(50.dp)
                                .background(Color.White, RoundedCornerShape(8.dp))
                                .border(1.dp, Color.White, RoundedCornerShape(8.dp))
                                .padding(12.dp)
                        )
                    }
                    item {
                        Text(
                            text = "Popular Movies",
                            color = Color.White,
                            fontSize = 18.sp,
                            modifier = Modifier.fillMaxWidth()
                                .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                        )
                    }
                    items(popularMovies) { movie ->
                        MovieItemView(movie)
                    }
                }

            }



            @Composable
            fun CustomMovieListViewHorizontal(movie: List<MovieData>) {
                LazyRow {
                    items(movie)
                    {
                        MovieItemView(it)
                    }
                }
            }

            @Composable
            fun CustomMovieListViewVertical(movie: List<MovieData>) {
                LazyColumn {
                    items(movie)
                    {
                        MovieItemView(it)
                    }
                }
            }
        }  )
}



