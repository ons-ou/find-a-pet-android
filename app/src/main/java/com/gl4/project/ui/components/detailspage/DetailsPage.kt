package com.gl4.project.ui.components.detailspage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gl4.project.data.entity.Animal
import com.gl4.project.data.utilities.ResourceState
import com.gl4.project.data.vm.AnimalsViewModel
import com.gl4.project.ui.components.ErrorBox

@Composable
fun DetailsPage(id: String, viewModel: AnimalsViewModel) {
    val animal by viewModel.animal.collectAsState()

    when (animal) {
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
            val state = (animal as ResourceState.Success<Animal>)
            PetDetails( animal = state.data )
        }
        is ResourceState.Error -> {
            ErrorBox(errorText = (animal as ResourceState.Error<Animal>).error, onClick = { viewModel.getAnimal(id) })
        }
    }
}