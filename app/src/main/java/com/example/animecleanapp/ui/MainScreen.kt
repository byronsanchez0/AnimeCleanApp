package com.example.animecleanapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.animecleanapp.TopBarWithFavoriteIcon
import com.example.animecleanapp.navigation.BottomNavGraph
import com.example.animecleanapp.ui.searchview.viewmodel.SearchAnimesViewModel
import com.example.newsapp.components.navigation.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBarWithFavoriteIcon(onFavoriteIconClick = { navController.navigate("favorites") }) },
        bottomBar = { BottomBar(navHostController = navController) }
    ) {
        it
        Box(modifier = Modifier.padding(it)) {
            BottomNavGraph(
                navController
            )
        }
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun MainScreenPreview() {
//    MainScreen()
//}