package com.example.animecleanapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetAnimeListUseCase
import com.example.domain.GetAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AnimesViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase,
    private val getAnimeUseCase: GetAnimeUseCase
):ViewModel() {

    fun getAnimeList(){
        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                val animes = getAnimeListUseCase.execute()
                println(animes)
            }
        }
    }
    fun getAnime(){
        val animes = getAnimeUseCase
        println(animes)
    }


}