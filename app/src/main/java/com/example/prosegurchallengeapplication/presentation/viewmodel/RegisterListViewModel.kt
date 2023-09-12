package com.example.prosegurchallengeapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prosegurchallengeapplication.domain.model.RegisterModel
import com.example.prosegurchallengeapplication.domain.usecase.RegisterListUseCase
import com.example.prosegurchallengeapplication.presentation.viewmodel.state.RegisterListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterListViewModel @Inject constructor(
    private val registerListUseCase: RegisterListUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(RegisterListUiState())
    val uiState: StateFlow<RegisterListUiState> = _uiState.asStateFlow()

    fun getAllPurchase() {
        viewModelScope.launch {
            val registers = registerListUseCase.getAllPurchase()
            _uiState.update {
                it.copy(
                    registerList = registers
                )
            }
        }
    }

    fun deleteRegister(registerModel: RegisterModel) {
        viewModelScope.launch {
            registerListUseCase.deleteRegister(registerModel)
            getAllPurchase()
        }
    }
}