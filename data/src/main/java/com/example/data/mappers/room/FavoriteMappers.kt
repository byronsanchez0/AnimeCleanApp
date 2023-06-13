package com.example.data.mappers.room

import com.example.data.local.EntityFavAnime
import com.example.domain.favorites.FavoriteAnime

fun EntityFavAnime.toFavoriteAnime(): FavoriteAnime {
    return FavoriteAnime(
        id = id,
        title = title,
        imageUrl = coverImage
    )
}

fun FavoriteAnime.toFavoriteAnimeEntity(): EntityFavAnime {
    return EntityFavAnime(
        id = id,
        title = title,
        coverImage = imageUrl
    )
}