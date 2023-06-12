package com.example.data.mappers

import com.example.data.type.MediaSort
import com.example.domain.search.model.AnimeSort

fun AnimeSort.toMediaSort():MediaSort{
    return when(this) {
        AnimeSort.POPULARITY -> MediaSort.POPULARITY
        AnimeSort.SCORE -> MediaSort.SCORE
        AnimeSort.STATUS -> MediaSort.STATUS
    }
}