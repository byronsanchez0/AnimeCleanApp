package com.example.animecleanapp.ui.favoritesview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.domain.favorites.FavoriteAnime
import java.net.URLEncoder



@Composable
fun FavoriteItem(
    favAnime: FavoriteAnime,
    navHostController: NavHostController,
    deleteFav: (FavoriteAnime) -> Unit
) {

    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.onTertiary)
    ) {
        Image(
            painter = rememberAsyncImagePainter(favAnime.imageUrl),
            contentDescription = "Movie Poster",
            modifier = Modifier
                .fillMaxSize()
                .clickable { navHostController.navigate("details/${favAnime.id}") }
        )
        Text(
            text = favAnime?.title ?: "",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(15.dp),
            softWrap = true,

            )
        FloatingActionButton(
            onClick = { deleteFav(favAnime) },
            containerColor = MaterialTheme.colorScheme.onSecondary,
            content = {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    contentDescription = "Delete"
                )
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}
