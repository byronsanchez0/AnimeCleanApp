package com.example.animecleanapp.ui.detailsview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.domain.details.model.AnimeDetailsModel

@Composable
fun DetailsScreen(
    id: Int,
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()

) {
    LaunchedEffect(key1 = id) {
        detailsViewModel.fetchAnimeDetails(id)
    }
    val animeDetails by detailsViewModel.anime.collectAsState(null)

    animeDetails?.let { details ->
        Column(
            modifier = Modifier
                .padding(start = 35.dp)
                .fillMaxSize()
        ) {
            DetailsScreenContent(
                animeDetails = details,
                seeCharacter = { characterId -> navController.navigate("character/$characterId") }
            )
        }

    }

}

@Composable
fun DetailsScreenContent(
    animeDetails: AnimeDetailsModel,
    seeCharacter: (Int) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ){
        Image(
            alignment = Alignment.Center,
            painter = rememberAsyncImagePainter(animeDetails.coverImage),
            contentDescription = "Movie Poster",
            modifier = Modifier.size(width = 350.dp, height = 350.dp)
        )
        Text(
            modifier = Modifier.padding(vertical = 20.dp),
            text = animeDetails.description,
            color = MaterialTheme.colorScheme.inverseSurface,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 3
        )
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = animeDetails.episodes.toString(),
            color = MaterialTheme.colorScheme.inverseSurface,
            style = MaterialTheme.typography.bodyMedium
        )
        CharacterItem(
            characters = animeDetails.characters ?: emptyList(),
            onCharacterClick = seeCharacter
        )
    }

}