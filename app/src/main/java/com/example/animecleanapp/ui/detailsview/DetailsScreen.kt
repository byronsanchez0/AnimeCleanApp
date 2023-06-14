package com.example.animecleanapp.ui.detailsview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.animecleanapp.R
import com.example.domain.details.model.AnimeDetailsModel
import com.example.domain.details.model.Character

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
                .fillMaxSize()
                .padding(horizontal = dimensionResource(id = R.dimen.mainhorizontalpadding10dp))
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
            .padding(horizontal = dimensionResource(id = R.dimen.spacersize12dp))
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacersize5dp)))
        Image(
            alignment = Alignment.Center,
            painter = rememberAsyncImagePainter(animeDetails.coverImage),
            contentDescription = stringResource(R.string.movie_poster),
            modifier = Modifier
                .size(width = 350.dp, height = 350.dp)
                .clip(RoundedCornerShape(30.dp))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacersize5dp)))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.spacersize12dp))
        ) {
            Text(

                text = stringResource(R.string.name),
                color = MaterialTheme.colorScheme.inverseSurface,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                modifier = Modifier.padding(vertical = 20.dp),
            )
            Text(

                text = animeDetails.name,
                color = MaterialTheme.colorScheme.inverseSurface,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                modifier = Modifier.padding(vertical = 20.dp),
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.spacersize12dp))
        ) {
            Text(
                text = stringResource(R.string.episodes),
                color = MaterialTheme.colorScheme.inverseSurface,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 10.dp),
            )
            Text(
                text = animeDetails.episodes.toString(),
                color = MaterialTheme.colorScheme.inverseSurface,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 10.dp),
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.spacersize12dp))
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacersize5dp)))
            Text(
                text = stringResource(R.string.users_score),
                color = MaterialTheme.colorScheme.inverseSurface,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 10.dp),
            )
            Text(
                text =  animeDetails.meanScore.toString(),
                color = MaterialTheme.colorScheme.inverseSurface,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 10.dp),
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.spacersize12dp))
        ){
            Text(
                text = stringResource(R.string.genres),
                color = MaterialTheme.colorScheme.inverseSurface,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 10.dp),
            )

        }
        animeDetails.genre?.take(3)?.forEach { genre ->
            Text(
                textAlign = TextAlign.End,
                text = genre,
                color = MaterialTheme.colorScheme.inverseSurface,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 10.dp),
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacersize5dp)))
        Text(
            textAlign = TextAlign.Justify,
            text = stringResource(R.string.anime_english_name) + animeDetails.englishName,
            color = MaterialTheme.colorScheme.inverseSurface,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacersize5dp)))
        Text(
            textAlign = TextAlign.Justify,
            text = stringResource(R.string.anime_japanese_name) + animeDetails.nativeName,
            color = MaterialTheme.colorScheme.inverseSurface,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacersize5dp)))
        Text(
            textAlign = TextAlign.Justify,
            text = animeDetails.description,
            color = MaterialTheme.colorScheme.inverseSurface,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacersize5dp)))
        CharacterItem(
            characters = animeDetails.characters ?: emptyList(),
            onCharacterClick = seeCharacter
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacersize12dp)))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun previewDetailsScreenContent() {
    val animeDetails = AnimeDetailsModel(
        id = 1,
        coverImage = R.drawable.brotherhood.toString(),
        name = "Anime Title",
        description = stringResource(R.string.naruto),
        episodes = 24,
        meanScore = 8,
        genre = listOf("Action", "Adventure", "Fantasy"),
        englishName = "English Anime Name",
        nativeName = "Japanese Anime Name",
        characters = listOf(
            Character(
                id = 1,
                name = "Character 1",
                image = R.drawable.brotherhood.toString(),
                age = "20",
                description = "hola esta es descripcion"
            ),
            Character(
                id = 1,
                name = "Character 1",
                image = "https://i.blogs.es/bc1dd2/naruto/840_560.png",
                age = "20",
                description = "hola esta es descripcion"
            ),
            Character(
                id = 1,
                name = "Character 1",
                image = "https://i.blogs.es/bc1dd2/naruto/840_560.png",
                age = "20",
                description = "hola esta es descripcion"
            )
        )
    )
    DetailsScreenContent(animeDetails = animeDetails, seeCharacter = {})
}