package com.example.domain

import com.example.domain.details.model.AnimeDetailsModel

class GetAnimeUseCase(
    private val animeRepo: AnimeRepo
) {
    suspend fun execute(id: Int): AnimeDetailsModel?{
        return animeRepo.getAnimesDetails(id)

    }

}