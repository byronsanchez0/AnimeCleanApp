package com.example.animecleanapp.ui.characterview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetCharacterDetailsUseCase
import com.example.domain.details.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
): ViewModel() {
    private val _character = MutableStateFlow<Character?>(null)
    val character: StateFlow<Character?> = _character

    fun fetchCharacterDetails(id: Int) {
        viewModelScope.launch {
            try {
                val details = getCharacterDetailsUseCase(id)
                _character.value = details
            } catch (e: Exception) {
                Log.d("Error","${e.message}")
            }
        }
    }
}
