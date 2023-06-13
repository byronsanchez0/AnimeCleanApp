package com.example.animecleanapp.ui.favoritesview

import com.example.domain.favorites.FavoriteAnime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class FavoriteUiState (
    val removeFromFavorites: (Int) -> Unit = {},
    val favoriteAnimesList: StateFlow<List<FavoriteAnime>> = MutableStateFlow(emptyList())
)