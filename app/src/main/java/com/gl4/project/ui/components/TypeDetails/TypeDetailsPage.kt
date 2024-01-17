package com.gl4.project.ui.components.TypeDetails

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
import com.gl4.project.data.entity.Type
import com.gl4.project.data.utilities.ResourceState
import com.gl4.project.data.vm.AnimalsViewModel
import com.gl4.project.ui.components.ErrorBox
import com.gl4.project.ui.components.detailspage.PetDetails

@Composable
fun TypeDetailsPage(type: String, viewModel: AnimalsViewModel) {
    val typeSelected by viewModel.typeSelected.collectAsState()

    when (typeSelected) {
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
            val state = (typeSelected as ResourceState.Success<Type>)
            TypeDetails( type = state.data )
        }
        is ResourceState.Error -> {

        }
    }
}