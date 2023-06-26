package com.example.animecleanapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.animecleanapp.ui.detailsview.DetailsViewModel
import com.example.domain.details.model.AnimeDetailsModel
import com.example.domain.details.model.Character
import com.example.domain.usecases.GetAnimeUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DetailsViewModelTest {

    @RelaxedMockK
    private lateinit var getAnimeDetailsUseCase: GetAnimeUseCase

    private lateinit var detailsViewModel: DetailsViewModel

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
    MockKAnnotations.init(this)
        detailsViewModel = DetailsViewModel(getAnimeDetailsUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }
    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }



    @Test
    fun fetchAnimeDetailsTest() = runTest {
        //Given
        val animeFake = MutableStateFlow(
            AnimeDetailsModel(
                id = 1,
                name = "Fake Anime",
                englishName = "English Name",
                nativeName = "Native Name",
                coverImage = "https://ingmaralbizu.com/wp-content/uploads/2021/04/642208.jpg",
                episodes = 25,
                meanScore = 8,
                genre = listOf("Drama", "Action", "Comedy"),
                description = "this is a fake anime",
                characters = listOf(
                    Character(
                        id = 1,
                        name = "goku",
                        age = "34",
                        image = "https://www.geekmi.news/__export/1641913206480/sites/debate/img/2022/01/11/rengoku_x2x.jpg_554688468.jpg",
                        description = "fake anime character 1"
                    ),
                    Character(
                        id = 2,
                        name = "vegeta",
                        age = "10",                        image = "https://wave.fr/images/1916/05/demon-slayer-nouveau-manga-phenomene-1.jpg",
                        description = "fake anime character 2"
                    ),
                    Character(
                        id = 3,
                        name = "elric",
                        age = "12",
                        image = "https://i0.wp.com/codigoespagueti.com/wp-content/uploads/2023/04/kimetsu-no-yaiba-husbando-mitsuri-fanart.jpg",
                        description = "fake anime character 3"
                    )
                )

            )
        )

        coEvery { getAnimeDetailsUseCase(1) } returns animeFake.value

        //when
        detailsViewModel.fetchAnimeDetails(1)

        //then
        assert(detailsViewModel.anime.value == animeFake.value)
    }
}