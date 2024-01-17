package com.gl4.project.ui.components.TypeDetails
import androidx.compose.runtime.Composable
import com.gl4.project.data.entity.Type
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun TypeDetails(type: Type) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(text = "Type: ${type.name}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Coats:")
            type.coats?.forEach { coat ->
                Text(" - $coat")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Colors:")
            type.colors?.forEach { color ->
                Text(" - $color")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Genders:")
            type.genders?.forEach { gender ->
                Text(" - $gender")
            }
        }
    }
}
