package org.example.moviekmm4.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import moviekmm4.composeapp.generated.resources.Res
import moviekmm4.composeapp.generated.resources.image3
import org.example.moviekmm4.Model.MovieData
import org.jetbrains.compose.resources.painterResource

@Composable
fun MovieItemView(movie: MovieData){
    Column (
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)


    ){
        Image(
            painter = painterResource(Res.drawable.image3),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.title,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
                .padding(start = 8.dp, bottom = 8.dp, end = 8.dp)
                .align(Alignment.CenterHorizontally)

        )
    }
}

