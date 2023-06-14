package com.example.animecleanapp.ui.CharacterDetails

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.animecleanapp.R
import com.example.animecleanapp.ui.searchview.composables.SearchScreen
import com.example.animecleanapp.ui.theme.AnimeCleanAppTheme
import com.example.domain.details.model.Character
import com.example.domain.search.model.AnimeModel
import kotlinx.coroutines.flow.flowOf


@Composable
fun CharacterDetailsScreen(
    characterId: Int,
    characterDetailsViewModel: CharacterDetailsViewModel = hiltViewModel()
) {
    val characterDetails by characterDetailsViewModel.character.collectAsState()

    characterDetails?.let { character ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            CharacterDetailsScreenContent(characterDetails = character)
        }

    } ?: run {
        characterDetailsViewModel.fetchCharacterDetails(characterId)
        Log.wtf("id no furula", "$characterId")
    }
}

@Composable
fun CharacterDetailsScreenContent(characterDetails: Character) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.mainhorizontalpadding10dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacersize5dp)))
                if (LocalInspectionMode.current) {
                    Image(
                        painter = painterResource(R.drawable.brotherhood),
                        contentDescription = "Image",
                        modifier = Modifier
                            .size(250.dp)
                            .clip(RectangleShape),
                        contentScale = ContentScale.Fit
                    )

                } else {
                    Image(
                        painter = rememberAsyncImagePainter(model = characterDetails.image),
                        contentDescription = "Image",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RectangleShape),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            characterDetails.name?.let { Text(text = it, textAlign = TextAlign.Center) }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacersize12dp)))
            characterDetails.age?.let { Text(text = "Age: $it") }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacersize12dp)))
            characterDetails.description?.let { Text(text = it) }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacersize12dp)))
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CharacterDetailsPreview() {
    val character = Character(
        id = 1,
        image = "https://i.blogs.es/bc1dd2/naruto/840_560.png",
        name = "Naruto",
        age = "30",
        description = stringResource(R.string.brotherhood)
    )
    AnimeCleanAppTheme {
        Box {
            CharacterDetailsScreenContent(character)
        }
    }
}