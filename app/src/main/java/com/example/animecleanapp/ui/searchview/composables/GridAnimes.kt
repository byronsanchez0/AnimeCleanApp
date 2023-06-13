package com.example.animecleanapp.ui.searchview.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.example.domain.search.model.AnimeModel

@Composable
fun GridAnimes (
    isLoading: Boolean,
    animeList: LazyPagingItems<AnimeModel>,
    navController: NavHostController,
    onToggleFavorite: (AnimeModel) -> Unit,
    favoriteAnime: Set<Int>,
    modifier: Modifier = Modifier

){
    Box {
        LazyVerticalGrid(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalArrangement = Arrangement.SpaceEvenly,
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize(),
        ) {
            items(animeList.itemCount) { anime ->
                val anime = animeList[anime]
                if (anime != null) {
                    AnimeItem(
                        anime = anime,
                        modifier = Modifier.fillMaxSize(),
                        onSelectedAnime = { println("Just seeing arounf") }
                    )
                }

            }
        }
        if (isLoading) {
            CircularProgressIndicator()
        }
    }
}