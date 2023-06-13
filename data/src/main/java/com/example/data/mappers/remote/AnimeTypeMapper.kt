package com.example.data.mappers.remote

import com.example.data.type.MediaType
import com.example.domain.search.model.AnimeType

fun AnimeType.toAnimeMediaType(): MediaType {
    return when (this){
        AnimeType.ANIME -> MediaType.ANIME
        AnimeType.MANGA -> MediaType.MANGA
    }
}