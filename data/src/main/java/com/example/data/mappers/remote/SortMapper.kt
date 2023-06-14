package com.example.data.mappers.remote

import com.example.data.type.MediaSort
import com.example.domain.search.model.AnimeSort

fun AnimeSort.toMediaSort(): MediaSort {
    return when (this) {
        AnimeSort.START_DATE -> MediaSort.START_DATE
        AnimeSort.EPISODES_DESC -> MediaSort.EPISODES_DESC
        AnimeSort.POPULARITY_DESC -> MediaSort.POPULARITY_DESC
    }
}
