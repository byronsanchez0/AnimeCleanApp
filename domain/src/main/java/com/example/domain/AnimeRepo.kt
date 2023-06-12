package com.example.domain

import com.example.domain.details.model.AnimeDetailsModel
import com.example.domain.search.model.AnimeModel
import com.example.domain.details.model.Character
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType

interface AnimeRepo {
    suspend fun getAnimes(
        page:Int,
        search:String? = null,
        sort:List<AnimeSort>,
        type: AnimeType
    ):List<AnimeModel>
    suspend fun getAnimesDetails(id: Int): AnimeDetailsModel?

    suspend fun getAnimeCharacters(id: Int): Character
}