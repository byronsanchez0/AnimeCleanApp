package com.example.domain.model

data class AnimeDetailsModel(
    val id: Int,
    val name: String,
    val englishName: String,
    val nativeName: String,
    val coverImage: String,
    val episodes: Int?,
    val meanScore: Int?,
    val genre: List<String>?,
    val description: String,
    val characters: List<Character>?
)
