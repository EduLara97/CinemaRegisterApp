package com.example.prosegurchallengeapplication.presentation.composables.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegisterField(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    Row (modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp, 4.dp, 8.dp, 4.dp)
    ) {
        Text(text = title)
        Text(text = value)
    }
}