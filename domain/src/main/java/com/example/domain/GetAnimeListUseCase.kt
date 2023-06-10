package com.example.domain

import com.example.domain.model.AnimeModel
import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(
    private val animeRepo: AnimeRepo
) {
    suspend fun execute(): List<AnimeModel>{
        return animeRepo
            .getAnimes()
            .sortedBy { it.title }

    }

}