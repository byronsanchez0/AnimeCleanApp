package com.example.animecleanapp.di
//
//import com.apollographql.apollo3.ApolloClient
//import com.example.data.reposimplementation.AnimeRepoImpl
//import com.example.domain.AnimeRepo
//import com.example.domain.GetAnimeListUseCase
//import com.example.domain.GetAnimeUseCase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ActivityComponent
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object AnimeModule {
//
//    @Provides
//    @Singleton
//    fun provideAnimeRepo(
//        apolloClient: ApolloClient,
//    ): AnimeRepo {
//        return AnimeRepoImpl(apolloClient)
//    }
//
//    @Provides
//    @Singleton
//    fun provideGetAnimeListUseCase(
//        animeRepo:AnimeRepo
//    ): GetAnimeListUseCase {
//        return GetAnimeListUseCase(animeRepo)
//    }
//
//    @Provides
//    @Singleton
//    fun provideGetAnimeUseCase(
//        animeRepo:AnimeRepo
//    ): GetAnimeUseCase {
//        return GetAnimeUseCase(animeRepo)
//    }
//
//}