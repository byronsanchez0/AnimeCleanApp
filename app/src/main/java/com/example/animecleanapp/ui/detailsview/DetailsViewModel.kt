package com.example.animecleanapp.ui.detailsview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetAnimeUseCase
import com.example.domain.details.model.AnimeDetailsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getAnimeUseCase : GetAnimeUseCase
) : ViewModel() {

    private val _anime = MutableStateFlow<AnimeDetailsModel?>(null)
    val anime: StateFlow<AnimeDetailsModel?> = _anime
    fun fetchAnimeDetails(id: Int) {
        viewModelScope.launch {
            try {
                val details = getAnimeUseCase(id)
                _anime.value = details
            } catch (e: Exception) {
                Log.d("Error","${e.message}")
            }
        }

    }
}
