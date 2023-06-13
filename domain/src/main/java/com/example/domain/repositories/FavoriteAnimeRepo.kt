package com.example.domain.repositories

import com.example.domain.favorites.FavoriteAnime
import kotlinx.coroutines.flow.Flow

interface FavoriteAnimeRepo {
    val favoriteAnime: Flow<List<FavoriteAnime>>

    suspend fun addFavoriteAnime(favoriteAnime: FavoriteAnime)

    suspend fun deleteFavoriteAnime(animeId: Int)
}