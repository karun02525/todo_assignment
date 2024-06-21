package com.sps.database.domain

import com.sps.database.data.dao.TodoDao
import com.sps.database.data.entity.TodoEntity
import javax.inject.Inject

class DatabaseService @Inject constructor(
    private val todoDao: TodoDao
) {
    suspend fun saveTodo(content: String) {
        todoDao.insertTodo(TodoEntity(notes = content))
    }

    suspend fun getAllTodos(): List<TodoEntity> {
        return todoDao.getTodo()
    }

    suspend fun getTodos(word: String): List<TodoEntity> {
        return todoDao.getTodos(word)
    }

}