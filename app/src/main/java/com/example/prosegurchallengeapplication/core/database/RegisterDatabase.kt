package com.example.prosegurchallengeapplication.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.prosegurchallengeapplication.data.database.dao.RegisterDao
import com.example.prosegurchallengeapplication.data.database.dao.RegisterListDao
import com.example.prosegurchallengeapplication.data.database.model.RegisterEntity
import com.example.prosegurchallengeapplication.data.database.model.RoomEntity

@Database(entities = [RegisterEntity::class, RoomEntity::class], version = 1)
abstract class RegisterDatabase : RoomDatabase() {

    abstract fun registerDao() : RegisterDao

    abstract fun registerListDao() : RegisterListDao
}