package com.example.prosegurchallengeapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.prosegurchallengeapplication.data.database.model.RegisterEntity
import com.example.prosegurchallengeapplication.data.database.model.RoomEntity

@Dao
interface RegisterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerPurchase(registerEntity: RegisterEntity)

    @Query("SELECT * FROM RoomEntity WHERE idRoom = :id AND day = :day")
    suspend fun getRoom(id: Int, day: String) : RoomEntity?

    @Query("UPDATE RoomEntity SET count = count + :count WHERE idRoom = :id AND day = :day")
    suspend fun updateRoomCount(count:Int, id:Int, day: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerRoom(roomEntity: RoomEntity)
}