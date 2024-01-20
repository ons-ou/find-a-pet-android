package com.gl4.project.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gl4.project.R
import com.gl4.project.data.repositories.AuthRepository
import com.gl4.project.data.vm.AnimalsViewModel
import com.gl4.project.ui.components.TypeDetails.TypeDetailsPage
import com.gl4.project.ui.components.auth.LoginPage
import com.gl4.project.ui.components.auth.RegisterPage
import com.gl4.project.ui.components.detailspage.DetailsPage
import com.gl4.project.ui.components.homepage.HomePage
import com.gl4.project.ui.theme.ProjectTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme {
                val navController = rememberNavController()
                val viewModel = AnimalsViewModel()
                val authRepository = AuthRepository()

                BackHandler {
                    if (navController.currentBackStackEntry?.destination?.route == "pets") {
                        finish()
                    }
                }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Find a pet") },
                            navigationIcon = {
                                Icon(
                                    painterResource(id = R.drawable.icon),
                                    "",
                                    Modifier.scale(0.5f)
                                )
                            },
                            modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                        )
                    }
                ) {
                    Column(
                        modifier = Modifier.padding(it)
                    ) {
                        NavHost(navController = navController, startDestination = "login") {
                            composable(route = "login") {
                                LoginPage(navController, authRepository)
                            }
                            composable(route = "register") {
                                RegisterPage(navController, authRepository)
                            }
                            composable(route = "pets") {
                                HomePage(navController, viewModel)
                            }
                            composable("pets/{id}") { backStackEntry ->
                                val id = backStackEntry.arguments?.getString("id") ?: ""
                                Log.d("nav", id)
                                DetailsPage(id = id, viewModel = viewModel)
                            }
                            composable("pets/type/{type}") { backStackEntry ->
                                val type = backStackEntry.arguments?.getString("type") ?: ""
                                Log.d("nav", type)
                                TypeDetailsPage(
                                    type = type,
                                    viewModel = viewModel,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}