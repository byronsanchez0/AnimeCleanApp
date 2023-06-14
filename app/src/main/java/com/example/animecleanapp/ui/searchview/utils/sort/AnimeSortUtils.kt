package com.example.animecleanapp.ui.searchview.utils.sort

import com.example.domain.search.model.AnimeSort

object AnimeSortUtils {
    private const val POPULARITY = "Popularity (Descending)"
    private const val EPISODES_DESC = "Episodes (Descending)"
    private const val START_DATE = "Start Date"

    val sortDisplayNames = mapOf(
        AnimeSort.POPULARITY_DESC to POPULARITY,
        AnimeSort.EPISODES_DESC to EPISODES_DESC,
        AnimeSort.START_DATE to START_DATE
    )
}

fun mapNameToAnimeSort(
    displayName: String,
    sortDisplayNames: Map<AnimeSort, String>
): AnimeSort? {
    return sortDisplayNames.entries.firstOrNull { it.value == displayName }?.key
}
