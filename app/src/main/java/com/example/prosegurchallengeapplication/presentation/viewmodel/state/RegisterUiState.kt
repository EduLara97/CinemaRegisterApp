package com.example.prosegurchallengeapplication.presentation.viewmodel.state

data class RegisterUiState(
    val onCompleteSave: Boolean = false,
    val onErrorCountRoom: Boolean = false,
    val errorMessage: String = ""
)
