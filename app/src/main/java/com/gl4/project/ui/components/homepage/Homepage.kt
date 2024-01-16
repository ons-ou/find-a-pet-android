package com.gl4.project.ui.components.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.gl4.project.data.entity.Animal
import com.gl4.project.data.utilities.ResourceState
import com.gl4.project.data.vm.AnimalsViewModel
import com.gl4.project.ui.components.ErrorBox

@Composable
fun HomePage(navController: NavController, viewModel: AnimalsViewModel) {
    val animalsState by viewModel.animalsResponse.collectAsState()
    val animalTypes by viewModel.types.collectAsState()

    when (animalsState) {
        is ResourceState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .wrapContentSize(Alignment.Center)
            ) {
                CircularProgressIndicator()
            }
        }
        is ResourceState.Success -> {
            val state = (animalsState as ResourceState.Success<List<Animal>>)
            Column {
                TypeSpinner(types = animalTypes) { b -> viewModel.changeType(b) }
                PetsPage(
                    animals = state.data,
                    onCardClicked = { animalId ->  navController.navigate("pets/$animalId")
                                    viewModel.getAnimal(animalId)},
                    onLoadMore = { viewModel.getAnimals() }
                )
            }
        }
        is ResourceState.Error -> {
            ErrorBox(errorText = (animalsState as ResourceState.Error<List<Animal>>).error, onClick = { viewModel.getAnimals()
            viewModel.getTypes() })
        }
    }
}