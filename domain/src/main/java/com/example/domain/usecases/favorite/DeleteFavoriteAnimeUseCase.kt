package com.example.domain.usecases.favorite

import com.example.domain.repositories.FavoriteAnimeRepo
import javax.inject.Inject

class DeleteFavoriteAnimeUseCase @Inject constructor(
    private val favoriteAnimeRepo: FavoriteAnimeRepo
) {
    suspend operator fun invoke(id: Int) {
        favoriteAnimeRepo.deleteFavoriteAnime(id)
    }
}
