package com.example.data.mappers

import com.example.data.GetAnimeListQuery
import com.example.domain.model.AnimeModel

fun GetAnimeListQuery.Medium.AnimeQueryListToAnimeList(): AnimeModel{
    return AnimeModel(
        id = id,
        title = title?.english.orEmpty(),
        coverImg = coverImage?.large.orEmpty()

    )
}