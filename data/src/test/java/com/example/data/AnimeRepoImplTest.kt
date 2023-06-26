package com.example.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.apollographql.apollo3.ApolloClient
import com.example.data.apolloextension.toOptional
import com.example.data.mappers.remote.toAnimeMediaType
import com.example.data.mappers.remote.toMediaSort
import com.example.data.reposimplementation.AnimeRepoImpl
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AnimeRepoImplTest {

    @RelaxedMockK
    private lateinit var apolloClient: ApolloClient

    lateinit var animeRepoImpl: AnimeRepoImpl

    private var queryAnime = GetAnimeListQuery(
        1.toOptional(),
        type = AnimeType.ANIME.toAnimeMediaType().toOptional(),
        sort = listOf(AnimeSort.POPULARITY_DESC).map { it.toMediaSort()}.toOptional(),
        search = "anime".toOptional()
    )

//    private var

    private val fakeAnimeList = listOf(
        AnimeModel(
            id = 1,
            coverImg = "image1",
            title = "anime1"
        ),
        AnimeModel(
            id = 2,
            coverImg = "image2",
            title = "anime2"
        ),
        AnimeModel(
            id = 3,
            coverImg = "image3",
            title = "anime3"
        )
    )


    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        apolloClient = ApolloClient.Builder()
            .networkTransport(apolloClient.networkTransport)
            .build()
        MockKAnnotations.init(this)
        animeRepoImpl = AnimeRepoImpl(apolloClient)
        Dispatchers.setMain(Dispatchers.Unconfined)

        coEvery {
            animeRepoImpl.getAnimes(
                any(),
                any(),
                any(),
                any()
            )
        } returns fakeAnimeList

    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun getAnimesTest() = runBlocking {
        //Given


        val fakeAnimeSortList = listOf(
            AnimeSort.EPISODES_DESC,
            AnimeSort.START_DATE
        )
        val fakeMediaType = AnimeType.ANIME
//        animeRepoImpl.getAnimes(
//            page = 1,
//            sort = listOf(
//                AnimeSort.EPISODES_DESC,
//                AnimeSort.START_DATE,
//                AnimeSort.POPULARITY_DESC
//            ),
//            type = AnimeType.ANIME,
//
//
//            )
//        coEvery {
//            animeRepoImpl.getAnimes(
//                1,
//                "anime",
//                fakeAnimeSortList,
//                AnimeType.ANIME
//            )
//        } returns fakeAnimeList

        apolloClient.

        //WHEN
        val response =
            animeRepoImpl.getAnimes(1, "anime", fakeAnimeSortList, fakeMediaType)

        //then
        assert(response == fakeAnimeList)
    }
}
