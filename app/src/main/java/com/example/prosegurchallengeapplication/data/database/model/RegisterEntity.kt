package com.example.prosegurchallengeapplication.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RegisterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val roomNumber: Int,
    val day: String,
    val ticketPrice: Double,
    val totalPrice: Double,
    val ticketsAmount: Int,
    val userDNI: String
)
