package com.example.data.mappers.remote

import com.example.data.GetCharacterDetailsQuery
import com.example.domain.details.model.Character

fun GetCharacterDetailsQuery.Character.toAnimeCharacter():Character{
    return Character(
        id = id,
        name = name?.full.orEmpty(),
        age = age?.orEmpty(),
        image = image?.medium.orEmpty(),
        description = description?.orEmpty()
    )
}