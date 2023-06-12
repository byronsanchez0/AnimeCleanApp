package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.example.data.database.AnimeDataBase
import com.example.data.local.AnimeRepoImpl
import com.example.domain.AnimeRepo
import com.example.domain.GetAnimeListUseCase
import com.example.domain.GetAnimeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://graphql.anilist.co")
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AnimeDataBase {
        return Room.databaseBuilder(
            appContext,
            AnimeDataBase::class.java,
            "AnimeDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAnimeRepo(
        apolloClient: ApolloClient,
    ): AnimeRepo {
        return AnimeRepoImpl(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetAnimeListUseCase(
        animeRepo: AnimeRepo
    ): GetAnimeListUseCase {
        return GetAnimeListUseCase(animeRepo)
    }

    @Provides
    @Singleton
    fun provideGetAnimeUseCase(
        animeRepo: AnimeRepo
    ): GetAnimeUseCase {
        return GetAnimeUseCase(animeRepo)
    }

    @Provides
    @Singleton
    fun provideCharacterUseCase(
        animeRepo: AnimeRepo
    ): GetAnimeUseCase {
        return GetAnimeUseCase(animeRepo)
    }
}