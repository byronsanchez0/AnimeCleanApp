package com.example.data.mappers.remote

import com.example.data.GetAnimeListQuery
import com.example.domain.search.model.AnimeModel

fun GetAnimeListQuery.Medium.AnimeQueryListToAnimeList(): AnimeModel {
    return AnimeModel(
        id = id,
        title = title?.romaji.orEmpty(),
        coverImg = coverImage?.extraLarge.orEmpty()
    )
}
