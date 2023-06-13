package com.example.animecleanapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.animecleanapp.DetailsScreen
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
            SearchScreen( navHostController)
        }
        composable("favorites") {
            FavoritesScreen()
        }
        composable(
            "details"
//            "details/{url}"
//            arguments = listOf(navArgument("url") { type = NavType.StringType })
        )
        {
//                backStackEntry ->
//            val url = backStackEntry.arguments?.getString("url") ?: ""
            DetailsScreen()
        }
    }
}
