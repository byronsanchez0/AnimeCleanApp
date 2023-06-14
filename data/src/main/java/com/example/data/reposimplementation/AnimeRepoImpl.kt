package com.example.data.reposimplementation

import com.apollographql.apollo3.ApolloClient
import com.example.data.GetAnimeDetailsQuery
import com.example.data.GetAnimeListQuery
import com.example.data.GetCharacterDetailsQuery
import com.example.data.apolloextension.executeQuery
import com.example.data.apolloextension.toOptional
import com.example.data.mappers.remote.AnimeDetailsQueryToAnimeDetails
import com.example.data.mappers.remote.AnimeQueryListToAnimeList
import com.example.data.mappers.remote.toAnimeCharacter
import com.example.data.mappers.remote.toMediaSort
import com.example.domain.AnimeRepo
import com.example.domain.details.model.AnimeDetailsModel
import com.example.domain.details.model.Character
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType
import javax.inject.Inject

class AnimeRepoImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : AnimeRepo {
    override suspend fun getAnimes(
        page: Int,
        search: String?,
        sort: List<AnimeSort>,
        type: AnimeType
    ): List<AnimeModel> {

        val query = GetAnimeListQuery(
            page = page.toOptional(),
            search = search.toOptional(),
            sort = sort.map { it.toMediaSort() }.toOptional()
        )
        return apolloClient.executeQuery(query) { data ->
            data
                ?.Page
                ?.media
                ?.mapNotNull { it?.AnimeQueryListToAnimeList() }
                ?: emptyList()
        }
    }


    override suspend fun getAnimeDetails(id: Int): AnimeDetailsModel? {
        val query = GetAnimeDetailsQuery(id.toOptional())
        return apolloClient.executeQuery(query) { data ->
            data.Media?.AnimeDetailsQueryToAnimeDetails()
        }


    }

    override suspend fun getAnimeCharacters(id: Int): Character {
        val query = GetCharacterDetailsQuery(id.toOptional())
        return apolloClient.executeQuery(query) { data ->
            data.Character?.toAnimeCharacter()
        }
    }
}