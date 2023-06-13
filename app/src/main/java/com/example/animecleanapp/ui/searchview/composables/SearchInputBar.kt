package com.example.animecleanapp.ui.searchview.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.animecleanapp.ui.searchview.viewmodel.SearchAnimesViewModel
import com.example.domain.search.model.AnimeModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchInputBar(
    onSearch:(String) -> Unit
) {
    var query by remember { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = { newValue ->
            query = newValue
            onSearch(newValue)
        },
        label = { Text("Search") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {

        }),
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier
                    .clickable {

                    }
            )
        }
    )
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun topBarPreview(){
//    SearchInputBar()
//}