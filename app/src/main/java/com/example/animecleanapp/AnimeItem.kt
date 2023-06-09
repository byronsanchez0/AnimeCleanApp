package com.example.animecleanapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.domain.model.AnimeModel

@Composable
fun AnimeItem(animeItem: AnimeModel) {
    Box(

    ) {
        Image(
                painter = painterResource(animeItem.coverImg.toInt()),
            contentDescription = "Movie Poster",
            modifier = Modifier.size(80.dp)
        )
    }

}