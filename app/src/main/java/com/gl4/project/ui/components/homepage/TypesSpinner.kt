package com.gl4.project.ui.components.homepage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gl4.project.data.entity.AnimalTypes
import com.gl4.project.data.utilities.ResourceState

@Composable
fun TypeSpinner(
    types: ResourceState<AnimalTypes>,
    onItemSelect: (String?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf("Cats") }

    when (types) {
        is ResourceState.Success -> {
            selectedType = "All"
            val typeNames: List<String> = listOf("All") + types.data.types.map { it.name }

            DropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                typeNames.forEach { type ->
                    DropdownMenuItem(
                        onClick = {
                            if (type == "All") {
                                onItemSelect(null)
                            } else {
                                onItemSelect(type)
                            }
                            expanded = false
                            selectedType = type
                        },
                        text = {
                            Text(
                                text = type,
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (type == selectedType) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface // Customize text color
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        is ResourceState.Loading -> {
            selectedType = "Loading..."
        }
        is ResourceState.Error -> {
            selectedType = "Error retrieving types"
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { expanded = true },
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = selectedType,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

