package com.example.prosegurchallengeapplication.presentation.viewmodel.state

import com.example.prosegurchallengeapplication.domain.model.RegisterModel

data class RegisterListUiState (
    val registerList: List<RegisterModel> = listOf()
)