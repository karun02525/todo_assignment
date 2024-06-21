package com.sps.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sps.database.data.entity.TodoEntity


@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoEntity)

    @Query("select * from TodoEntity ORDER BY id DESC")
    suspend fun getTodo(): List<TodoEntity>

    @Query("select * from TodoEntity WHERE notes LIKE '%' ||:word|| '%'")
    suspend fun getTodos(word :String): List<TodoEntity>


}