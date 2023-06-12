package com.example.animecleanapp.ui.searchview.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animecleanapp.ui.searchview.viewmodel.SearchAnimesViewModel
import com.example.domain.search.model.AnimeModel


@Composable
fun SearchScreen (
    viewModel: SearchAnimesViewModel
){
    val loading = viewModel.isLoading.collectAsState()
    val animes = viewModel.animeFlow.collectAsLazyPagingItems()
    SearchScreenContent(
        isLoading = loading.value,
        animeList = animes,
        viewModel.onSearch
//        onSelectedAnime = uiState.onSelectAnime
    )
}

@Composable
fun SearchScreenContent(
    isLoading:Boolean,
    animeList: LazyPagingItems<AnimeModel>,
    onSearch:(String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 80.dp, start = 16.dp, end = 16.dp)
            .fillMaxSize()
    ) {
        SearchInputBar(onSearch)
        Spacer(modifier = Modifier.height(16.dp))
        Box {
            LazyVerticalGrid(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalArrangement = Arrangement.SpaceEvenly,
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize(),
            ){
                items(animeList.itemCount){pucta ->
                    val anime = animeList[pucta]
                    if (anime != null) {
                        AnimeItem(
                            anime = anime,
                            modifier = Modifier.fillMaxSize(),
                            onSelectedAnime = { println("Just seeing arounf") }
                        )
                    }

                }
            }
            if (isLoading){
                CircularProgressIndicator()
            }
        }

    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SearchScreenPrev() {
//    val viewModel: SearchViewModel = hiltViewModel()
//    SearchAnimeScreen(viewModel = viewModel)
//}
