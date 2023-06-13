package com.example.animecleanapp.ui.favoritesview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.domain.favorites.FavoriteAnime
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.StateFlow
import java.net.URLEncoder

@OptIn(ExperimentalPagerApi::class)

@Composable
fun FavoritesScreen(
    navController: NavHostController,
    favoriteViewModel: FavoritesViewModel = hiltViewModel()

) {
    val favoriteUiState by favoriteViewModel.uiStateFavorite.collectAsState()

    FavoritesScreenContent(
        favoriteAnimeFlowList = favoriteUiState.favAnimeList,
        deleteFav = favoriteUiState.deleteFromFavorites,
        navHostController = navController
    )
}

@Composable
fun FavoritesScreenContent(
    favoriteAnimeFlowList: StateFlow<List<FavoriteAnime>>,
    deleteFav: (Int) -> Unit,
    navHostController: NavHostController
) {

    val favoriteAnimeList by favoriteAnimeFlowList.collectAsState(emptyList())
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = favoriteAnimeList.size,
            state = pagerState,
            itemSpacing = 5.dp
        ) { page ->
            Card(
                modifier = Modifier
                    .size(width = 375.dp, height = 425.dp)
                    .padding(16.dp)
                    .graphicsLayer {
                        val pageOffset =
                            (pagerState.currentPage - page) + pagerState.currentPageOffset
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                            .also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                    }
            ) {
                val favAnime = favoriteAnimeList[page]
                FavoriteItem(
                    favAnime = favAnime,
                    navHostController = navHostController,
                    deleteFav = { anime -> deleteFav(anime.id) })
            }
        }
    }
}

const val CARD_WIDTH_FACTOR = 0.7f
const val CARD_HEIGHT_FACTOR = 0.7f
const val CARD_WIDTH_FACTOR_LANDSCAPE = 0.4f
const val CARD_HEIGHT_FACTOR_LANDSCAPE = 0.5f
const val PAGER_SNAP_DISTANCE = 4

