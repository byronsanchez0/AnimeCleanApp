package com.example.animecleanapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.animecleanapp.R
import com.example.animecleanapp.ui.theme.AnimeCleanAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithFavoriteIcon(onFavoriteIconClick: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        title = {
            Text(
                text = stringResource(R.string.anime),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        actions = {
            IconButton(onClick = onFavoriteIconClick) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(R.string.favorite),
                )
            }
        }
    )
}

@Preview
@Composable
fun TopBarWithFavoriteIconPreview() {
    AnimeCleanAppTheme {
        TopBarWithFavoriteIcon(onFavoriteIconClick = { /* Do something here */ })
    }
}
