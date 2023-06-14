package com.example.domain

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
