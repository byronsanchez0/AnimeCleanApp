package com.example.domain.usecases

import com.example.domain.repositories.AnimeRepo
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType
import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(
    private val animeRepo: AnimeRepo
) {
    suspend fun execute(
        page: Int,
        search: String? = null,
        sort: List<AnimeSort>,
        type: AnimeType
    ): List<AnimeModel> {
        return animeRepo
            .getAnimes(page, search, sort, type)
    }
}
