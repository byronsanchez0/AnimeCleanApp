package com.example.animecleanapp

import com.example.domain.model.AnimeModel
import kotlinx.coroutines.flow.Flow

data class AnimeUiState(
    val animes: List<AnimeModel> = emptyList(),
    val isLoading: Boolean = false,
    val searchNews: (String) -> Unit,
//    val saveSelectedFilter: (Filter) -> Unit,
//    val selectedFilter: Flow<Filter>
)
