package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.example.data.local.DaoFavAnime
import com.example.data.local.FavAnimeDataBase
import com.example.data.reposimplementation.AnimeRepoImpl
import com.example.data.reposimplementation.FavoriteRepoImpl
import com.example.domain.repositories.AnimeRepo
import com.example.domain.usecases.GetAnimeListUseCase
import com.example.domain.usecases.GetAnimeUseCase
import com.example.domain.usecases.GetCharacterDetailsUseCase
import com.example.domain.repositories.FavoriteAnimeRepo
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
    fun provideFavDatabase(@ApplicationContext appContext: Context): FavAnimeDataBase {
        return Room.databaseBuilder(
            appContext,
            FavAnimeDataBase::class.java,
            "AnimeFavDatabase"
        ).build()
    }

    @Provides
    fun provideFavoriteAnimeDao(database: FavAnimeDataBase): DaoFavAnime {
        return database.animeDao()
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
    fun provideFavAnimeRepo(
        daoFavAnime: DaoFavAnime,
    ): FavoriteAnimeRepo {
        return FavoriteRepoImpl(daoFavAnime)
    }

    //useCases
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
    ): GetCharacterDetailsUseCase {
        return GetCharacterDetailsUseCase(animeRepo)
    }
}
