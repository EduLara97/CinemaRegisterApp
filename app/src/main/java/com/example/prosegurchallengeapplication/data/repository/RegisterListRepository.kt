package com.example.prosegurchallengeapplication.data.repository

import com.example.prosegurchallengeapplication.data.database.dao.RegisterListDao
import com.example.prosegurchallengeapplication.data.database.model.RegisterEntity
import com.example.prosegurchallengeapplication.domain.model.RegisterModel
import com.example.prosegurchallengeapplication.helpers.toRegisterEntity
import javax.inject.Inject

class RegisterListRepository @Inject constructor(
    private val registerListDao: RegisterListDao
) {

    suspend fun getAllPurchase() : List<RegisterEntity> = registerListDao.getRegisterList()

    suspend fun deleteRegister(registerModel: RegisterModel) {
        registerListDao.deleteRegister(registerModel.toRegisterEntity())
        registerListDao.updateRoomCount(registerModel.ticketsAmount, registerModel.roomNumber, registerModel.day)

    }


}