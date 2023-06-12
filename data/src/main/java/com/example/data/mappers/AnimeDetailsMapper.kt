package com.example.data.mappers

import com.example.data.GetAnimeDetailsQuery
import com.example.domain.details.model.AnimeDetailsModel
import com.example.domain.details.model.Character

fun GetAnimeDetailsQuery.Media.AnimeDetailsQueryToAnimeDetails(): AnimeDetailsModel {
    return AnimeDetailsModel(
        id = id,
        name = title?.romaji.orEmpty(),
        englishName = title?.english.orEmpty(),
        nativeName = title?.native.orEmpty(),
        coverImage = coverImage?.large.orEmpty(),
        episodes = episodes,
        meanScore = meanScore,
        genre = genres?.mapNotNull { it },
        description = description.orEmpty(),
        characters = characters?.nodes?.mapNotNull { character ->
            character?.let {
                Character(
                    id = id,
                    name = it.name?.first,
                    age = it.age,
                    image = it.image?.medium.orEmpty(),
                    description = it.description
                )
            }
        }



    )
}