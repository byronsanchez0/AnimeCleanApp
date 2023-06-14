package com.example.domain.usecases.favorite

import com.example.domain.favorites.FavoriteAnime
import com.example.domain.repositories.FavoriteAnimeRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesAnimesUseCase @Inject constructor(
    private val favoriteAnimeRepo: FavoriteAnimeRepo
) {
    operator fun invoke(): Flow<List<FavoriteAnime>> {
        return favoriteAnimeRepo.favoriteAnime
    }
}
