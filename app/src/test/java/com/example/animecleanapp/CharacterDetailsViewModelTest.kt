package com.example.animecleanapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.animecleanapp.ui.characterview.CharacterDetailsViewModel
import com.example.domain.details.model.Character
import com.example.domain.usecases.GetCharacterDetailsUseCase
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

class CharacterDetailsViewModelTest {

    @RelaxedMockK
    private lateinit var getCharacterDetailsUseCase: GetCharacterDetailsUseCase

    private lateinit var characterDetailsViewModel: CharacterDetailsViewModel



    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        characterDetailsViewModel = CharacterDetailsViewModel(getCharacterDetailsUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }


    @Test
    fun fetchAnimeDetailsTest() = runTest {
        //Given
        val characterFake = MutableStateFlow(

            Character(
                id = 1,
                name = "elric",
                age = "12",
                image = "https://i0.wp.com/codigoespagueti.com/wp-content/uploads/2023/04/kimetsu-no-yaiba-husbando-mitsuri-fanart.jpg",
                description = "fake anime character 3"
            )

        )

        coEvery { getCharacterDetailsUseCase(1) } returns characterFake.value

        //when
        characterDetailsViewModel.fetchCharacterDetails(1)

        //then
        assert(characterDetailsViewModel.character.value == characterFake.value)
    }

}