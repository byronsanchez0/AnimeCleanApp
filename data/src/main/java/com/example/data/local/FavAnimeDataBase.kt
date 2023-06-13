package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EntityFavAnime::class], version = 1)
abstract class FavAnimeDataBase : RoomDatabase() {
    abstract fun animeDao(): DaoFavAnime
}