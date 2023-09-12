package com.example.prosegurchallengeapplication.domain.usecase

import com.example.prosegurchallengeapplication.data.database.model.RegisterEntity
import com.example.prosegurchallengeapplication.data.repository.RegisterListRepository
import com.example.prosegurchallengeapplication.domain.model.RegisterModel
import com.example.prosegurchallengeapplication.helpers.toRegisterModel
import javax.inject.Inject

class RegisterListUseCase @Inject constructor(
    private val registerListRepository: RegisterListRepository
) {

    suspend fun getAllPurchase() : List<RegisterModel> {
        val response: List<RegisterEntity> = registerListRepository.getAllPurchase()
        return response.map { it.toRegisterModel() }
    }

    suspend fun deleteRegister(registerModel: RegisterModel) =
        registerListRepository.deleteRegister(registerModel)

}