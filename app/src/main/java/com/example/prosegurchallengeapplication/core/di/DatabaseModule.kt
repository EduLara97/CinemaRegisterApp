package com.example.prosegurchallengeapplication.core.di

import android.content.Context
import androidx.room.Room
import com.example.prosegurchallengeapplication.core.database.RegisterDatabase
import com.example.prosegurchallengeapplication.data.database.dao.RegisterDao
import com.example.prosegurchallengeapplication.data.database.dao.RegisterListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val REGISTER_DATABASE = "register_database"

    @Provides
    fun provideRegisterDao(registerDatabase: RegisterDatabase) : RegisterDao =
        registerDatabase.registerDao()

    @Provides
    fun provideRegisterListDao(registerDatabase: RegisterDatabase) : RegisterListDao =
        registerDatabase.registerListDao()

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RegisterDatabase::class.java, REGISTER_DATABASE).build()
}