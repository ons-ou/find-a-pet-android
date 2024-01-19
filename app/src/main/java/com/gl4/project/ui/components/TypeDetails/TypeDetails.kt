package com.gl4.project.ui.components.TypeDetails
import androidx.compose.runtime.Composable
import com.gl4.project.data.entity.Type
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TypeDetails(type: Type, onNavigateClick: (String, String, String) -> Unit) {
    var selectedCoat by remember { mutableStateOf<String?>(null) }
    var selectedColor by remember { mutableStateOf<String?>(null) }
    var selectedGender by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(text = "Type: ${type.name}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Text("Coat:")

            type.coats?.forEach { coat ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (selectedCoat == coat),
                        onClick = { selectedCoat = coat }
                    )
                    Text(
                        text = coat,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Color:")

            type.colors?.forEach { coat ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (selectedColor == coat),
                        onClick = { selectedColor = coat }
                    )
                    Text(
                        text = coat,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Gender:")

            type.genders?.forEach { coat ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (selectedGender == coat),
                        onClick = { selectedGender = coat }
                    )
                    Text(
                        text = coat,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                val coatParam = selectedCoat ?: ""
                val colorParam = selectedColor ?: ""
                val genderParam = selectedGender ?: ""

                onNavigateClick(coatParam, colorParam, genderParam)
            }) {
                Text(text = "Filter Results")
            }
        }
    }
}
