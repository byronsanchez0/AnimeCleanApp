package com.example.animecleanapp.ui.CharacterDetails

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.animecleanapp.R
import com.example.domain.details.model.Character


@Composable
fun CharacterDetailsScreen(
    characterId: Int,
    characterDetailsViewModel: CharacterDetailsViewModel = hiltViewModel()
) {
//    LaunchedEffect(key1 = characterId) {
//        characterDetailsViewModel.fetchCharacterDetails(characterId)
//    }
    val characterDetails by characterDetailsViewModel.character.collectAsState()

    characterDetails?.let { character ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            CharacterDetailsScreenContent(characterDetails = character)
        }

    } ?: run { characterDetailsViewModel.fetchCharacterDetails(characterId)
        Log.wtf("id no furula", "$characterId")}
}

@Composable
fun CharacterDetailsScreenContent(characterDetails: Character) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 20.dp
            ),
    ) {
        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                if (LocalInspectionMode.current) {
                    Image(
                        painter = painterResource(R.drawable.brotherhood),
                        contentDescription = "Image",
                        modifier = Modifier
                            .size(200.dp)
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
//            Text(text = characterDetails.name)
            characterDetails.name?.let { Text(text = it) }
            characterDetails.age?.let { Text(text = it) }
            characterDetails.description?.let { Text(text = it) }
        }

    }
}
