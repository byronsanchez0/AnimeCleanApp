package com.example.animecleanapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var icon: ImageVector, var route: String) {
    object SearchAnime : BottomNavItem(
        Icons.Filled.Search,
        "animesearchscreen"
    )

    object  Favorites: BottomNavItem(
        Icons.Outlined.Favorite,
        "favorites"
    )
}
