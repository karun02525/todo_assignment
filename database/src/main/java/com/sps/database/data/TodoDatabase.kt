package com.sps.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sps.database.common.Config.DATABASE_VERSION
import com.sps.database.data.dao.TodoDao
import com.sps.database.data.entity.TodoEntity


@Database(entities = [TodoEntity::class], version = DATABASE_VERSION)
abstract class TodoDatabase : RoomDatabase() {
    abstract val dao: TodoDao
}