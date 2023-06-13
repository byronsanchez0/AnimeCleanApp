package com.example.animecleanapp.ui.favoritesview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.favorites.FavoriteAnime
import com.example.domain.usecases.favorite.DeleteFavoriteAnimeUseCase
import com.example.domain.usecases.favorite.GetFavoritesAnimesUseCase
import com.example.domain.usecases.favorite.UpdateFavoriteAnimesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteAnimeUseCase: GetFavoritesAnimesUseCase,
    private val deleteFavoriteAnimeUseCase: DeleteFavoriteAnimeUseCase,
    private val updateFavoriteAnimesUseCase: UpdateFavoriteAnimesUseCase
) : ViewModel() {
    private val _favAnimeList = MutableStateFlow<List<FavoriteAnime>>(emptyList())

    private val _uiState = MutableStateFlow(
        FavUiState(
            favoriteAnime = emptySet()
        )
    )

    private val _uiStateFavorite = MutableStateFlow(
        FavoriteUiState(
            deleteFromFavorites = this::deleteFromFavorites,
            favAnimeList = _favAnimeList
        )
    )

    val uiStateFavorite = _uiStateFavorite.asStateFlow()

    init {
        viewModelScope.launch {
            getFavoriteAnimeUseCase()
                .onEach { favoriteAnimeList ->
                    _favAnimeList.value = favoriteAnimeList
                    _uiState.value =
                        _uiState.value.copy(favoriteAnime = favoriteAnimeList.map { favAnime ->
                            favAnime.id
                        }.toSet())
                }.launchIn(viewModelScope)

            updateFavoriteAnimesUseCase().collect { updatedFavoriteAnime ->
                _uiState.value = _uiState.value.copy(favoriteAnime = updatedFavoriteAnime)
            }
        }
    }

    private fun deleteFromFavorites(animeId: Int) {
        viewModelScope.launch {
            if (_uiState.value.favoriteAnime.contains(animeId)) {
                deleteFavoriteAnimeUseCase(animeId)
            }
        }
    }


}