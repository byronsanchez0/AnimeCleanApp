package com.example.animecleanapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.animecleanapp.ui.searchview.utils.paging.AnimePagingSource
import com.example.animecleanapp.ui.searchview.viewmodel.SearchAnimesViewModel
import com.example.domain.search.model.AnimeModel
import com.example.domain.usecases.GetAnimeListUseCase
import com.example.domain.usecases.GetAnimeUseCase
import com.example.domain.usecases.favorite.AddFavoriteAnimeUseCase
import com.example.domain.usecases.favorite.DeleteFavoriteAnimeUseCase
import com.example.domain.usecases.favorite.UpdateFavoriteAnimesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @RelaxedMockK
    private lateinit var getAnimeListUseCase: GetAnimeListUseCase
    @RelaxedMockK
    private lateinit var addFavoriteAnimeUseCase: AddFavoriteAnimeUseCase
    @RelaxedMockK
    private lateinit var deleteFavoriteAnimeUseCase: DeleteFavoriteAnimeUseCase
    @RelaxedMockK
    private lateinit var updateFavoriteAnimesUseCase: UpdateFavoriteAnimesUseCase
    @RelaxedMockK
    private lateinit var pagingSource: AnimePagingSource

    private lateinit var searchAnimesViewModel: SearchAnimesViewModel

    @get: Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        searchAnimesViewModel = SearchAnimesViewModel(getAnimeListUseCase, addFavoriteAnimeUseCase, deleteFavoriteAnimeUseCase, updateFavoriteAnimesUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }
    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun searchTest() = runTest{

        //Given
//        val fakeAnimePagingSource = AnimePagingSource(
//            getAnimeListUseCase,
//            loadingUI = true,
////            sort =
//
//        )

        val fakeAnimeFlow = AnimeModel(
            id = 1,
            title = "samba",
            coverImg = "https://i0.wp.com/codigoespagueti.com/wp-content/uploads/2023/04/kimetsu-no-yaiba-husbando-mitsuri-fanart.jpg"
        )

        coEvery { }

        //when
        searchAnimesViewModel.animeFlow

    }

}