package com.example.prosegurchallengeapplication.data.repository

import com.example.prosegurchallengeapplication.data.database.dao.RegisterDao
import com.example.prosegurchallengeapplication.data.database.model.RoomEntity
import com.example.prosegurchallengeapplication.domain.model.RegisterModel
import com.example.prosegurchallengeapplication.domain.model.RoomModel
import com.example.prosegurchallengeapplication.helpers.toRegisterEntity
import com.example.prosegurchallengeapplication.helpers.toRoomEntity
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val registerDao: RegisterDao
) {

    suspend fun getRoom(id: Int, day: String) : RoomEntity? = registerDao.getRoom(id, day)

    suspend fun registerRoom(roomModel: RoomModel) {
        registerDao.registerRoom(roomModel.toRoomEntity())
    }

    suspend fun registerPurchase(registerModel: RegisterModel ) {
        registerDao.registerPurchase(registerModel.toRegisterEntity())

    }

    suspend fun updateRoom(registerModel: RegisterModel) {
        registerDao.updateRoomCount(registerModel.ticketsAmount, registerModel.roomNumber, registerModel.day)
    }
}