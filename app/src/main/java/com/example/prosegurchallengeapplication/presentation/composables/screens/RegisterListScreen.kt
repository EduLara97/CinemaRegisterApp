package com.example.prosegurchallengeapplication.presentation.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.prosegurchallengeapplication.domain.model.RegisterModel
import com.example.prosegurchallengeapplication.presentation.composables.components.RegisterList
import com.example.prosegurchallengeapplication.presentation.viewmodel.RegisterListViewModel

@Composable
fun RegisterListScreen(
    modifier: Modifier = Modifier,
    registerListViewModel: RegisterListViewModel
) {

    val state by registerListViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {registerListViewModel.getAllPurchase()})

    Column (modifier = modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleMedium,
            text = "Register Buy List"
        )
        RegisterList(
            registerList = state.registerList,
            deleteRegister = {
                registerListViewModel.deleteRegister(it)
            }
        )
    }

}