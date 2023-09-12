package com.example.prosegurchallengeapplication.helpers

import com.example.prosegurchallengeapplication.data.database.model.RegisterEntity
import com.example.prosegurchallengeapplication.data.database.model.RoomEntity
import com.example.prosegurchallengeapplication.domain.model.RegisterModel
import com.example.prosegurchallengeapplication.domain.model.RoomModel


fun RegisterModel.toRegisterEntity() : RegisterEntity =
    RegisterEntity(
        id = id,
        roomNumber = roomNumber,
        day = day,
        ticketPrice = ticketPrice,
        totalPrice = totalPrice,
        ticketsAmount = ticketsAmount,
        userDNI = userDNI
    )

fun RegisterEntity.toRegisterModel() : RegisterModel =
    RegisterModel(
        id = id,
        roomNumber = roomNumber,
        day = day,
        ticketPrice = ticketPrice,
        totalPrice = totalPrice,
        ticketsAmount = ticketsAmount,
        userDNI = userDNI
    )

fun RoomEntity.toRoomModel() : RoomModel =
    RoomModel(
        idRoom = idRoom,
        count = count,
        day = day
    )

fun RoomModel.toRoomEntity() : RoomEntity =
    RoomEntity(
        idRoom = idRoom,
        count = count,
        day = day
    )