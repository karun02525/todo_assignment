package com.sps.assessmentdotoapp.data.repository

import com.sps.assessmentdotoapp.domain.model.Note

interface TodoRepository {

    suspend fun saveTodo(content: String)

    suspend fun getAllTodos():List<Note>

    suspend fun getTodos(word :String):List<Note>
}