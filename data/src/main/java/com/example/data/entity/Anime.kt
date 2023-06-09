package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Anime(
    @PrimaryKey
    val id: Int,
    val title: String,
    val coverImg: String,
)
