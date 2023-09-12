package com.example.prosegurchallengeapplication.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prosegurchallengeapplication.presentation.composables.screens.HomeScreen
import com.example.prosegurchallengeapplication.presentation.composables.screens.RegisterListScreen
import com.example.prosegurchallengeapplication.presentation.composables.screens.RegisterScreen
import com.example.prosegurchallengeapplication.presentation.viewmodel.RegisterListViewModel
import com.example.prosegurchallengeapplication.presentation.viewmodel.RegisterViewModel
import com.example.prosegurchallengeapplication.ui.theme.ProsegurChallengeApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val registerViewModel: RegisterViewModel by viewModels()
        val registerListViewModel : RegisterListViewModel by viewModels()
        setContent {
            ProsegurChallengeApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home" ) {
                        composable(
                            route = "home",
                            content = {
                                HomeScreen(navController = navController)
                            }
                        )
                        composable(
                            route = "register",
                            content = {
                                RegisterScreen(registerViewModel = registerViewModel, navController = navController)
                            }
                        )
                        composable(
                            route = "list",
                            content = {
                                RegisterListScreen(registerListViewModel = registerListViewModel)
                            }
                        )
                    }
                }
            }
        }
    }
}