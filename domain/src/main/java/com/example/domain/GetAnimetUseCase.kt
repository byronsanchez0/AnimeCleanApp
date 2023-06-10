package com.example.domain

import com.example.domain.model.AnimeDetailsModel
import com.example.domain.model.AnimeModel

class GetAnimeUseCase(
    private val animeRepo: AnimeRepo
) {
    suspend fun execute(id: Int): AnimeDetailsModel?{
        return animeRepo.getAnimesDetails(id)

    }

}