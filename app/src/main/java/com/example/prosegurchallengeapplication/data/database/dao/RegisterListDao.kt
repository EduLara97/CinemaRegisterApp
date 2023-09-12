package com.example.prosegurchallengeapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.prosegurchallengeapplication.data.database.model.RegisterEntity

@Dao
interface RegisterListDao {

    @Query("SELECT * FROM RegisterEntity")
    suspend fun getRegisterList(): List<RegisterEntity>

    @Delete
    suspend fun deleteRegister(register: RegisterEntity)

    @Query("UPDATE RoomEntity SET count = count - :count WHERE idRoom = :id AND day = :day")
    suspend fun updateRoomCount(count:Int, id:Int, day: String)

}