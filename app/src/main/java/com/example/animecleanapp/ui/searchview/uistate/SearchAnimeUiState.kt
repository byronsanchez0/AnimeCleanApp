package com.example.animecleanapp.ui.searchview.uistate

import com.example.domain.details.model.AnimeDetailsModel
import com.example.domain.search.model.AnimeModel

data class SearchAnimeUiState(
    val isLoading:Boolean,
    val selectedAnime:AnimeDetailsModel?,
    val anime:List<AnimeModel>,
    val onSelectAnime:(Int) -> Unit
)
