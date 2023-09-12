package com.example.prosegurchallengeapplication.helpers


/**
 *
 * Estos datos en un ambito real se deberian de obtener de un api externo, pero en este caso el reto solo
 * solicita que los datos de registros persistan en el dispositivo. Con lo cual, estos datos abajo se
 * usaran para simular todos los casos a cubrir
 *
 * */

const val BASIC_PRICE = 32
const val MAX_LIMIT_ROOM = 60
const val LUNES = "Lunes"
const val MARTES = "Martes"
const val MIERCOLES = "Miercoles"
const val JUEVES = "Jueves"
const val VIERNES = "Viernes"
const val SABADO = "Sabado"
const val DOMINGO = "Domingo"

val ROOM_LIST = listOf("1","2","3","4","5","6")
val DAYS = listOf(LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO)
val MOVIES_BY_ROOM = mapOf("1" to "Movie 1", "2" to "Movie 2", "3" to "Movie 3", "4" to "Movie 4", "5" to "Movie 5", "6" to "Movie 6")