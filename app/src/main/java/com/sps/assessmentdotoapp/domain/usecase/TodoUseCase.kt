package com.sps.assessmentdotoapp.domain.usecase

import com.sps.assessmentdotoapp.domain.model.Note

interface TodoUseCase {

    suspend fun saveTodo(content: String)

    suspend fun getAllTodos():List<Note>

    suspend fun getTodos(word :String):List<Note>

}