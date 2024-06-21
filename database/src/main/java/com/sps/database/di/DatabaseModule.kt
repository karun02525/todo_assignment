package com.sps.database.di

import android.app.Application
import androidx.room.Room
import com.sps.database.common.Config.DATABASE_NAME
import com.sps.database.data.TodoDatabase
import com.sps.database.domain.DatabaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTodoDataBase(app: Application): TodoDatabase {
        return Room.databaseBuilder(app, TodoDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseService(db: TodoDatabase) = DatabaseService(db.dao)
}