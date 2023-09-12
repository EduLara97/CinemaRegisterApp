package com.example.prosegurchallengeapplication.presentation.composables.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.prosegurchallengeapplication.domain.model.RegisterModel

@Composable
fun RegisterList(
    modifier: Modifier = Modifier,
    registerList: List<RegisterModel>,
    deleteRegister: (RegisterModel) -> Unit
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(registerList) { register ->
            RegisterCard(register = register, deleteRegister = deleteRegister)
        }
    }

}