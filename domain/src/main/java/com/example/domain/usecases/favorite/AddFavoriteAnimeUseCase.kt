package com.example.domain.usecases.favorite

import com.example.domain.favorites.FavoriteAnime
import com.example.domain.repositories.FavoriteAnimeRepo
import com.example.domain.search.model.AnimeModel
import javax.inject.Inject

class AddFavoriteAnimeUseCase @Inject constructor( private val favoriteAnimeRepo: FavoriteAnimeRepo) {
    suspend operator fun invoke(anime: AnimeModel){
        val favoriteAnime = FavoriteAnime(
            id = anime.id,
            title = anime.title,
            imageUrl = anime.coverImg
        )
        favoriteAnimeRepo.addFavoriteAnime(favoriteAnime)
    }
}
