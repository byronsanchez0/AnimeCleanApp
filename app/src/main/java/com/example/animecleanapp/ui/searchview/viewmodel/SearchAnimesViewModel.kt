package com.example.animecleanapp.ui.searchview.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.animecleanapp.paging.AnimePagingSource
import com.example.animecleanapp.ui.searchview.uistate.SearchAnimeUiState
import com.example.domain.GetAnimeListUseCase
import com.example.domain.GetAnimeUseCase
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchAnimesViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase,
    private val getDetailsUseCase: GetAnimeUseCase
):ViewModel() {

    private val _search = MutableStateFlow<String?>("")
    private val _type = MutableStateFlow(AnimeType.ANIME)
    private val _sort = MutableStateFlow<List<AnimeSort>>(listOf())

    private val _isLoading = MutableStateFlow(false)
    private val _selectedAnime = MutableStateFlow<AnimeModel?>(null)
    val isLoading = _isLoading.asStateFlow()
    val selectedAnime = _selectedAnime.asStateFlow()
    val onSearch = ::search

    private val _uiState = MutableStateFlow(
        SearchAnimeUiState(
            isLoading = false,
            selectedAnime = null,
            anime = emptyList(),
            onSelectAnime = ::selectedAnime
        )
    )

    val uiState = _uiState.asStateFlow()


    fun search(text: String) {
        viewModelScope.launch {
            _search.emit(text)
        }
    }

    private fun selectedAnime(id:Int){
        viewModelScope.launch {
            _uiState.update {it.copy (
                selectedAnime = getDetailsUseCase.execute(id)
            )

            }
        }
    }

    private fun createPaging(
        type:AnimeType,
        sort:List<AnimeSort>,
        search:String?
    ): Pager<Int, AnimeModel> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { AnimePagingSource(getAnimeListUseCase,search,sort,type, _isLoading) }
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val animeFlow: Flow<PagingData<AnimeModel>> = combine(_type, _sort, _search) { type, sort, search ->
        Triple(type,sort, search)
    }.flatMapLatest { (type,sort, search) ->
        createPaging(type,sort,search).flow
    }.cachedIn(viewModelScope)

//    fun getAnimeList(query : String){
//        viewModelScope.launch{
//            withContext(Dispatchers.IO) {
//                val animes = getAnimeListUseCase.execute()
//                println(animes)
//            }
//        }
//    }
//    fun getAnime(){
//        val animes = getAnimeUseCase
//        println(animes)
//    }
companion object{
    private const val PAGE_SIZE = 10
}

}