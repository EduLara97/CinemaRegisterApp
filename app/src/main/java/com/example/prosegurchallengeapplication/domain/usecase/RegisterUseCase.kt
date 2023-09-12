package com.example.prosegurchallengeapplication.domain.usecase

import com.example.prosegurchallengeapplication.data.repository.RegisterRepository
import com.example.prosegurchallengeapplication.domain.model.RegisterModel
import com.example.prosegurchallengeapplication.domain.model.RoomModel
import com.example.prosegurchallengeapplication.helpers.toRoomModel
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
){

    suspend fun getRoom(id:Int, day: String) : RoomModel? {
        return registerRepository.getRoom(id, day)?.toRoomModel()
    }

    suspend fun registerRoom(roomModel: RoomModel) = registerRepository.registerRoom(roomModel)

    suspend fun updateRoom(registerModel: RegisterModel) = registerRepository.updateRoom(registerModel)

    suspend fun registerPurchase(registerModel: RegisterModel) =
        registerRepository.registerPurchase(registerModel)

}