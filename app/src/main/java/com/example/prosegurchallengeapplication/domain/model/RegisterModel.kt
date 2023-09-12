package com.example.prosegurchallengeapplication.domain.model

data class RegisterModel(
    val id: Int,
    val roomNumber: Int,
    val day: String,
    val ticketPrice: Double,
    val totalPrice: Double,
    val ticketsAmount: Int,
    val userDNI: String
)