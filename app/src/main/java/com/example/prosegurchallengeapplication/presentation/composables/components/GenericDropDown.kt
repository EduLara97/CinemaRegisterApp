package com.example.prosegurchallengeapplication.presentation.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prosegurchallengeapplication.ui.theme.ProsegurChallengeApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericDropDown(
    modifier: Modifier = Modifier,
    optionList: List<String> = listOf(),
    placeHolder: String = "",
    getElementSelected: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        modifier = modifier.fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = { isExpanded ->
            expanded = isExpanded
        }) {

        TextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            placeholder = {
                Text(text = placeHolder)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )
        DropdownMenu(
            modifier =  Modifier.exposedDropdownSize(),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            optionList.forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = it)
                    },
                    onClick = {
                        value = it
                        getElementSelected(it)
                        expanded = !expanded
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewGenericDropDown() {
    ProsegurChallengeApplicationTheme {
        Surface (modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            GenericDropDown(optionList = listOf("a","a","a","a","a","a")){}
        }
    }
}