package com.example.animecleanapp.ui.searchview.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.animecleanapp.R
import com.example.domain.search.model.AnimeModel


@Composable
fun AnimeItem(
    anime: AnimeModel,
    onSelectedFavAnime: (AnimeModel) -> Unit,
    favoriteAnime: Set<Int>,
    navController: NavController,
    modifier: Modifier
) {
    val painter = rememberAsyncImagePainter(model = anime.coverImg)
    val aFavAnime = favoriteAnime.contains(anime.id)

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .clickable {
                navController.navigate("details/${anime.id}")
            },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (LocalInspectionMode.current) {
                Image(
                    modifier = Modifier.aspectRatio(3 / 4f),
                    painter = painterResource(id = R.drawable.brotherhood),
                    contentDescription = "Image",
                    alignment = Alignment.Center
                )
            } else {
                Image(
                    modifier = Modifier.aspectRatio(3 / 4f),
                    painter = painter,
                    contentDescription = "Image",
                    alignment = Alignment.Center
                )
            }
            Text(
                text = anime.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                modifier = Modifier.padding(start = 8.dp)
            )
            IconButton(onClick = { onSelectedFavAnime(anime) }) {
                Icon(
                    imageVector = if (aFavAnime) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favorite"
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ItemPreview() {
    val navController = rememberNavController()
    val anime = AnimeModel(
        id = 1,
        title = "Naruto Shippuden",
        coverImg = R.drawable.brotherhood.toString()
    )
    AnimeItem(
        anime = anime,
        modifier = Modifier,
        favoriteAnime = setOf(1),
        navController = navController,
        onSelectedFavAnime = {})
}
