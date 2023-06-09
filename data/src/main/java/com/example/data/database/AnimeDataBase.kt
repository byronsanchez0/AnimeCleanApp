package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.AnimeDao
import com.example.data.entity.Anime

@Database(entities = [Anime::class], version = 1)
abstract class AnimeDataBase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}