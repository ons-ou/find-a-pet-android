package com.gl4.project.ui.components.detailspage

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.gl4.project.data.entity.Animal
import com.gl4.project.ui.sampledata.SampleAnimal

@Composable
fun PetDetails(animal: Animal) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),

    ) {
        animal.photos.firstOrNull()?.let { photo ->
            Image(
                painter = rememberAsyncImagePainter(photo.medium),
                contentDescription = "Animal Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
            )
            Spacer(modifier = Modifier.padding(5.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.9f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = animal.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            TwoTexts(text1 = "Age: ", text2 = animal.age)
            TwoTexts(text1 = "Gender: ", text2 = animal.gender)
            TwoTexts(text1 = "Primary Breed: ", text2 = animal.breeds.primary ?: "N/A")
            TwoTexts(text1 = "Secondary Breed: ", text2 = animal.breeds.secondary ?: "N/A")


            if (animal.environment.children) {
                Text(
                    text = "Suitable For Children",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            if (animal.environment.dogs) {
                Text(
                    text = "Likes other dogs",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                animal.tags.map { tag ->
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colorScheme.onSecondaryContainer)
                    ) {
                        Text(
                            text = tag,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
            if (animal.description != null) {
                Text(
                    text = animal.description,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}


@Preview(showBackground = true, name = "Light Mode")
@Composable
fun PreviewPetDetailsLight() {
    MaterialTheme {
        PetDetails(animal = SampleAnimal())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun PreviewPetDetailsDark() {
    MaterialTheme {
        PetDetails(animal = SampleAnimal())
    }
}



@Composable
fun TwoTexts(
    text1: String,
    text2: String,
) {
    Row {
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            modifier = Modifier
                .padding(end = 8.dp)
                .wrapContentWidth(Alignment.CenterHorizontally),
            text = text2
        )
    }
}
