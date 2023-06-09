package com.example.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.Anime

@Dao
interface AnimeDao {
    @Query("SELECT * FROM Anime")
    fun getFavorites(): List<Anime>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(animeEntity: Anime)

    @Delete
    fun deleteFavorite(animeEntity: Anime)
}