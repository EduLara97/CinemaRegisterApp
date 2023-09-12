package com.example.prosegurchallengeapplication.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idRoom: Int,
    val count: Int,
    val day: String
)