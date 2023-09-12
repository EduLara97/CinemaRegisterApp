package com.example.prosegurchallengeapplication

import com.example.prosegurchallengeapplication.domain.model.RegisterModel
import com.example.prosegurchallengeapplication.domain.model.RoomModel

val roomModel = RoomModel(
    1,
    10,
    "Lunes"
)

val registerModel = RegisterModel(
    1,
    1,
    "Lunes",
    32.00,
    64.00,
    2,
    "7777777"
)

val roomModelError = RoomModel(
    1,
    50,
    "Lunes"
)

val registerModelError = RegisterModel(
    1,
    1,
    "Lunes",
    32.00,
    64.00,
    20,
    "7777777"
)

val idRoom = 1
val day = "Lunes"