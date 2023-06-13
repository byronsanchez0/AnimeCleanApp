package com.example.domain

import com.example.domain.details.model.AnimeDetailsModel
import javax.inject.Inject

class GetAnimeUseCase @Inject constructor(
    private val animeRepo: AnimeRepo
) {
    suspend operator fun invoke(id: Int): AnimeDetailsModel?{
        return animeRepo.getAnimeDetails(id)

    }

}