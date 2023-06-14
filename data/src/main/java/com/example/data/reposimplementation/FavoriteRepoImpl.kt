package com.example.data.reposimplementation

import com.example.data.local.DaoFavAnime
import com.example.data.local.EntityFavAnime
import com.example.data.mappers.room.toFavoriteAnime
import com.example.data.mappers.room.toFavoriteAnimeEntity
import com.example.domain.favorites.FavoriteAnime
import com.example.domain.repositories.FavoriteAnimeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepoImpl @Inject constructor(
    private val favoriteAnimeDao: DaoFavAnime
) : FavoriteAnimeRepo {
    override val favoriteAnime: Flow<List<FavoriteAnime>>
        get() = favoriteAnimeDao.getFavoriteAnime()
            .map { list ->
                list.map(EntityFavAnime::toFavoriteAnime)
            }

    override suspend fun addFavoriteAnime(favoriteAnime: FavoriteAnime) {
        favoriteAnimeDao.addFavoriteAnime(favoriteAnime.toFavoriteAnimeEntity())
    }

    override suspend fun deleteFavoriteAnime(animeId: Int) {
        favoriteAnimeDao.deleteFavoriteAnime(animeId)
    }
}
