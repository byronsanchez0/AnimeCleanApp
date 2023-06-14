package com.example.domain.usecases.favorite

import com.example.domain.repositories.FavoriteAnimeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpdateFavoriteAnimesUseCase @Inject constructor(
    private val favoriteRepo:FavoriteAnimeRepo){
    operator fun invoke(): Flow<Set<Int>> {
        return favoriteRepo.favoriteAnime.map { list ->
            list.map { it.id }.toSet()
        }
    }
}
