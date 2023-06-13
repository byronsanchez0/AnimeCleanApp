package com.example.animecleanapp.ui.favoritesview

import com.example.domain.favorites.FavoriteAnime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class FavoriteUiState (
    val deleteFromFavorites: (Int) -> Unit = {},
    val favAnimeList: StateFlow<List<FavoriteAnime>> = MutableStateFlow(emptyList())
)