package com.example.animecleanapp.ui.searchview.uistate

import com.example.domain.details.model.AnimeDetailsModel
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType

data class SearchAnimeUiState(
    val onTypeChanged: (AnimeType) -> Unit = {},
    val onSortChanged: (AnimeSort) -> Unit = {},
    val onSearchChanged: (String) -> Unit = {},
    val addToFavorites: (AnimeModel) -> Unit = {},
    val favoriteAnime: Set<Int> = emptySet()
    )
