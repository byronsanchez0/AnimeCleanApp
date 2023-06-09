package com.example.data.local

import com.apollographql.apollo3.ApolloClient
import com.example.data.GetAnimeDetailsQuery
import com.example.data.GetAnimeListQuery
import com.example.data.mappers.AnimeDetailsQueryToAnimeDetails
import com.example.data.mappers.AnimeQueryListToAnimeList
import com.example.domain.AnimeRepo
import com.example.domain.model.AnimeDetailsModel
import com.example.domain.model.AnimeModel

class AnimeRepoImpl(
    private val apolloClient: ApolloClient
) : AnimeRepo {
    override suspend fun getAnimes(): List<AnimeModel> {

        return apolloClient
            .query(GetAnimeListQuery())
            .execute()
            .data
            ?.Page
            ?.media
            ?.mapNotNull { it?.AnimeQueryListToAnimeList() }
            ?: emptyList()

    }

    override suspend fun getAnimesDetails(id: Int): AnimeDetailsModel? {

        return apolloClient
            .query(GetAnimeDetailsQuery())
            .execute()
            .data
            ?.Media
            ?.AnimeDetailsQueryToAnimeDetails()

    }
}