package com.example.prosegurchallengeapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prosegurchallengeapplication.domain.model.RegisterModel
import com.example.prosegurchallengeapplication.domain.model.RoomModel
import com.example.prosegurchallengeapplication.domain.usecase.RegisterUseCase
import com.example.prosegurchallengeapplication.helpers.MAX_LIMIT_ROOM
import com.example.prosegurchallengeapplication.presentation.viewmodel.state.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel  @Inject constructor(
    private val registerUseCase: RegisterUseCase
)  : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun registerPurchase(registerModel: RegisterModel) {
        viewModelScope.launch {
            val roomRegister: RoomModel? = registerUseCase.getRoom(registerModel.roomNumber, registerModel.day)
            if (roomRegister == null || checkAvailability(roomRegister, registerModel.ticketsAmount)) {
                registerUseCase.registerPurchase(registerModel)
                if (roomRegister == null){
                    registerUseCase.registerRoom(RoomModel(registerModel.roomNumber, registerModel.ticketsAmount, registerModel.day))
                } else {
                    registerUseCase.updateRoom(registerModel)
                }
                _uiState.update {
                    it.copy(
                        onCompleteSave = true
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        onErrorCountRoom = true,
                        errorMessage = "La cantidad de tickets supera el aforo"
                    )
                }
            }

        }
    }

    private fun checkAvailability(roomModel: RoomModel, newTickets: Int) : Boolean{
        return roomModel.count + newTickets <= MAX_LIMIT_ROOM
    }

    fun registerErrorMessage(errorMessage: String) {
        _uiState.update {
            it.copy(
                onErrorCountRoom = errorMessage.isNotEmpty(),
                errorMessage = errorMessage
            )
        }
    }

    fun resetUiState(){
        _uiState.update {
            it.copy(
                onCompleteSave = false,
                onErrorCountRoom = false,
                errorMessage = ""
            )
        }
    }

}