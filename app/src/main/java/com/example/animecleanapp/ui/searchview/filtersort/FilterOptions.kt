package com.example.animecleanapp.ui.searchview.filtersort

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animecleanapp.R
import com.example.animecleanapp.ui.searchview.utils.AnimeSortUtils
import com.example.animecleanapp.ui.searchview.utils.mapNameToAnimeSort
import com.example.animecleanapp.ui.theme.AnimeCleanAppTheme
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType

@Composable
fun FilterOptions(
    type: AnimeType,
    sort: AnimeSort,
    onTypeSelected: (AnimeType) -> Unit,
    onSortSelected: (AnimeSort) -> Unit
) {
    val typeItems = AnimeType.values().map { it.name }
    val sortItems = AnimeSort.values().map { AnimeSortUtils.sortDisplayNames[it] ?: it.name }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        SelectFilterDropdown(
            items = typeItems,
            selectedItem = type.name,
            onItemSelected = { selected ->
                onTypeSelected(AnimeType.valueOf(selected))
            })
        SelectFilterDropdown(
            items = sortItems,
            selectedItem = AnimeSortUtils.sortDisplayNames[sort] ?: sort.name,
            onItemSelected = { selected ->
                mapNameToAnimeSort(
                    selected, AnimeSortUtils.sortDisplayNames
                )?.let { selectedSort ->
                    onSortSelected(selectedSort)
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewFilterOptions() {
    val selectedType = AnimeType.ANIME
    val selectedSort = AnimeSort.EPISODES_DESC
    AnimeCleanAppTheme {
        FilterOptions(
            type = selectedType,
            sort = selectedSort,
            onTypeSelected = {
                // Handle type selection
            },
            onSortSelected = {
                // Handle sort selection
            }
        )
    }
}