package com.example.animecleanapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.animecleanapp.ui.CharacterDetails.CharacterDetailsScreen
import com.example.animecleanapp.ui.detailsview.DetailsScreen
import com.example.animecleanapp.ui.favoritesview.FavoritesScreen
import com.example.animecleanapp.ui.searchview.composables.SearchScreen

@Composable
fun BottomNavGraph(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = "animesearchscreen"
    ) {
        composable("animesearchscreen") {
            SearchScreen(navHostController)
        }
        composable("favorites") {
            FavoritesScreen(navHostController)
        }
        composable(
            "details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        )
        { backStackEntry ->
            backStackEntry.arguments?.getInt("id")?.let { id ->
                DetailsScreen(id, navHostController)
            }

        }
        composable(
            "character/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        )
        { backStackEntry ->
            backStackEntry.arguments?.getInt("characterId")?.let { characterId ->
                CharacterDetailsScreen(characterId)
            }

        }
    }
}
