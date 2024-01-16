package com.gl4.project.ui.components.homepage

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.gl4.project.data.entity.Animal

@Composable
fun PetsPage(animals: List<Animal>, onCardClicked: (id: String)-> Unit, onLoadMore: () -> Unit) {

    LazyColumn {
        items(animals) { animal ->
            PetInfoCard(animal, onCardClicked)
        }

        item {
            LoadMore(onLoadMore = onLoadMore)
        }
    }
}
