package com.example.domain.usecases

import com.example.domain.repositories.AnimeRepo
import com.example.domain.details.model.Character
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(private val animeRepo: AnimeRepo) {
    suspend operator fun invoke(id: Int): Character {
        return animeRepo.getAnimeCharacters(id)
    }
}
