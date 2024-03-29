package com.example.animecleanapp.ui.searchview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animecleanapp.ui.searchview.composables.GridAnimes
import com.example.animecleanapp.ui.searchview.composables.SearchInputBar
import com.example.animecleanapp.ui.searchview.utils.filtersort.FilterOptions
import com.example.animecleanapp.ui.searchview.viewmodel.SearchAnimesViewModel
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType


@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchAnimesViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsState()
    val uiStateSearch by viewModel.uiSearchState.collectAsState()
    val loading = viewModel.isLoading.collectAsState()
    val anime = viewModel.animeFlow.collectAsLazyPagingItems()

    SearchScreenContent(
        isLoading = loading.value,
        animeList = anime,
        onSearch = uiStateSearch.onSearchChanged,
        onTypeChanged = uiStateSearch.onTypeChanged,
        onSortChanged = uiStateSearch.onSortChanged,
        onToggleFavorite = { animes -> uiStateSearch.addToFavorites(animes) },
        favoriteAnime = uiState.favoriteAnime,
        navController = navController,
    )
}

@Composable
fun SearchScreenContent(
    isLoading: Boolean,
    animeList: LazyPagingItems<AnimeModel>,
    onSearch: (String) -> Unit,
    onTypeChanged: (AnimeType) -> Unit,
    onSortChanged: (AnimeSort) -> Unit,
    onToggleFavorite: (AnimeModel) -> Unit,
    favoriteAnime: Set<Int>,
    navController: NavHostController,
) {

    var selectedType by rememberSaveable { mutableStateOf(AnimeType.ANIME) }
    val onTypeChangedState by rememberUpdatedState(onTypeChanged)
    var selectedSort by rememberSaveable { mutableStateOf(AnimeSort.POPULARITY_DESC) }
    val onSortChangedState by rememberUpdatedState(onSortChanged)


    Column(
        modifier = Modifier
            .padding(top = 10.dp, start = 16.dp, end = 16.dp)
            .fillMaxSize()
    ) {
        SearchInputBar(onSearch)
        Spacer(modifier = Modifier.height(3.dp))
        FilterOptions(type = selectedType, sort = selectedSort, onTypeSelected = { type ->
            selectedType = type
            onTypeChangedState(type)
        }, onSortSelected = { sort ->
            selectedSort = sort
            onSortChangedState(sort)
        })
        GridAnimes(
            isLoading = isLoading,
            animeList = animeList,
            navController = navController,
            onToggleFavorite = onToggleFavorite,
            favoriteAnime = favoriteAnime,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPrev() {
    val navController = rememberNavController()
    SearchScreen(navController)
}
