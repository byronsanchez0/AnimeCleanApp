package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoFavAnime {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteAnime(favoriteAnimeEntity: EntityFavAnime)

    @Query("DELETE FROM favoriteAnimes WHERE id = :animeId")
    suspend fun deleteFavoriteAnime(animeId: Int)

    @Query("SELECT * FROM favoriteAnimes")
    fun getFavoriteAnime(): Flow<List<EntityFavAnime>>
}
