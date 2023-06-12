package com.example.animecleanapp.ui.searchview.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.animecleanapp.R
import com.example.domain.search.model.AnimeModel


@Composable
fun AnimeItem(
    anime: AnimeModel,
    onSelectedAnime:(Int) -> Unit,
    modifier: Modifier
) {
    val painter = rememberAsyncImagePainter(model = anime.coverImg)
    Card (
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable { onSelectedAnime(anime.id) },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ){
        if(LocalInspectionMode.current){
            Image(
                modifier = modifier.aspectRatio(3/4f),
                painter = painterResource(id = R.drawable.brotherhood),
                contentDescription = "Image",
                alignment = Alignment.Center
            )
        }else{
            Image(
                modifier = modifier.aspectRatio(3/4f),
                painter = painter,
                contentDescription = "Image",
                alignment = Alignment.Center
            )
        }
        Text(
            modifier = modifier.fillMaxSize(),
            text = anime.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            maxLines = 1,
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ItemPreview() {
    val anime = AnimeModel(
        id = 1,
        title = "Naruto Shippuden",
        coverImg = R.drawable.brotherhood.toString()
    )
    AnimeItem(anime = anime, modifier = Modifier, onSelectedAnime = {})
}