package com.example.prosegurchallengeapplication.presentation.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.prosegurchallengeapplication.ui.theme.ProsegurChallengeApplicationTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column (modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onClick = { navController.navigate("register") }
        ) {
            Text(text = "Register Purchase")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate("list") }
        ) {
            Text(text = "Register List")
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    ProsegurChallengeApplicationTheme {
        Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            HomeScreen(modifier = Modifier.fillMaxSize(), navController = rememberNavController())
        }
    }
}