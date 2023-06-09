package com.example.domain

import com.example.domain.model.AnimeDetailsModel
import com.example.domain.model.AnimeModel

interface AnimeRepo {
    suspend fun getAnimes(

        //pagination
    ): List<AnimeModel>
    suspend fun getAnimesDetails(id: Int): AnimeDetailsModel?
}